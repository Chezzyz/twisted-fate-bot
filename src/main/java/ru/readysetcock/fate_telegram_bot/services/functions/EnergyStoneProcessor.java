package ru.readysetcock.fate_telegram_bot.services.functions;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.readysetcock.fate_telegram_bot.formatters.FeaturesFormatter;
import ru.readysetcock.fate_telegram_bot.messages.BotApiMethodFactory;
import ru.readysetcock.fate_telegram_bot.messages.InlineKeyboardBuilder;
import ru.readysetcock.fate_telegram_bot.messages.Response;
import ru.readysetcock.fate_telegram_bot.model.domain.EnergyStone;
import ru.readysetcock.fate_telegram_bot.repository.EnergyStoneRepository;
import ru.readysetcock.fate_telegram_bot.services.commands.BotCommand;
import ru.readysetcock.fate_telegram_bot.services.commands.BotCommandProcessor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ru.readysetcock.fate_telegram_bot.messages.InlineKeyboardBuilder.button;
import static ru.readysetcock.fate_telegram_bot.messages.InlineKeyboardBuilder.rowOf;

@Service
@RequiredArgsConstructor
public class EnergyStoneProcessor implements BotFunctionProcessor, BotCommandProcessor {

    private final EnergyStoneRepository energyStoneRepository;

    @Override
    public BotFunction getFunction() {
        return BotFunction.STONES;
    }

    @Override
    public BotCommand getCommand() {
        return BotCommand.STONES;
    }

    @Override
    public Response process(Message message) {
        return sendButtonsWithEnergyStones(message);
    }

    @Override
    public Response process(CallbackQuery query) {
        String data = query.getData();
        if (data.equals(BotFunction.STONES.getFunctionName())) {
            return editToButtonsWithEnergyStones(query);
        } else if (data.contains(BotFunction.STONES.getFunctionName() + "/id/")) {
            return sendEnergyStoneInfo(query);
        } else if (data.contains(BotFunction.STONES.getFunctionName() + "/delete")) {
            return deleteStone(query);
        }

        return new Response();
    }

    private Response editToButtonsWithEnergyStones(CallbackQuery query) {
        Message message = query.getMessage();
        return new Response(BotApiMethodFactory.messageEdit(message.getChatId(), message.getMessageId(), "Выберите энергетический камень", getAllEnergyStonesKeyboard()));
    }

    private Response sendButtonsWithEnergyStones(Message message) {
        return new Response(BotApiMethodFactory.inlineKeyboardMessage(message.getChatId(), "Выберите энергетический камень", getAllEnergyStonesKeyboard()));
    }

    private Response sendEnergyStoneInfo(CallbackQuery query) {
        int id = Integer.parseInt(query.getData().replace(BotFunction.STONES.getFunctionName() + "/id/", ""));
        EnergyStone energyStone = getEnergyStoneById(id);
        if (energyStone == null) {
            return new Response(BotApiMethodFactory.callbackQueryAnswer(query.getId()));
        }
        return deleteOnlyStoneResponse(energyStone, query);
    }

    private Response deleteOnlyStoneResponse(EnergyStone energyStone, CallbackQuery query) {
        SendPhoto sendPhoto = BotApiMethodFactory.messageWithPhoto(query.getMessage().getChatId(), energyStone.getImageFileId(),
                createEnergyStoneInfoString(energyStone),
                InlineKeyboardBuilder.createKeyboardOf(rowOf(button("⬅ Назад", "%s/delete".formatted(BotFunction.STONES.getFunctionName())))));

        return Response.builder()
                .photo(sendPhoto)
                .methods(Collections.singletonList(BotApiMethodFactory.callbackQueryAnswer(query.getId())))
                .build();
    }

    private Response deleteStone(CallbackQuery query) {
        Message message = query.getMessage();
        return new Response(BotApiMethodFactory.deleteMessage(message.getChatId(), message.getMessageId()));
    }

    private EnergyStone getEnergyStoneById(int id) {
        return energyStoneRepository.findById(id).orElse(null);
    }

    private InlineKeyboardMarkup getAllEnergyStonesKeyboard() {
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        energyStoneRepository.findAll().forEach(stone -> buttons.add(button(stone.getRusName(),
                BotFunction.STONES.getFunctionName() + "/id/" + stone.getId())));
        buttons.add(button("⬅ Назад", BotFunction.CATALOGUE.getFunctionName()));
        return InlineKeyboardBuilder.createKeyboardOf(buttons);
    }

    private String createEnergyStoneInfoString(EnergyStone energyStone) {
        return """
                <b>Название</b>: %s (%s)

                <b>Свойства</b>:
                %s
                <b>Описание</b>: %s

                %s""".formatted(
                energyStone.getRusName(),
                energyStone.getEngName(),
                FeaturesFormatter.formatPrettyFeatures(energyStone.getFeatures().split(", ")),
                energyStone.getRealDescription(),
                energyStone.getEsotericDescription()
        );
    }
}




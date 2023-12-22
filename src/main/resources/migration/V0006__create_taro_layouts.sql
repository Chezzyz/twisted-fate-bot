DROP TABLE IF EXISTS taro_layouts;
CREATE TABLE taro_layouts
(
    id            smallint     NOT NULL,
    rus_name      varchar(255) NOT NULL,
    eng_name      varchar(255) NOT NULL,
    scheme_info   text         NOT NULL,
    description   text         NOT NULL,
    symbol        varchar(8)   NOT NULL,
    image_file_id varchar(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (id)
);
INSERT INTO taro_layouts (id, rus_name, eng_name, scheme_info, description, symbol, image_file_id)
VALUES  (1, 'Кельтский крест', 'Celtic cross', '1.Смысл проблемы&2.Привходящие обстоятельства&3.Что мы об этом думаем&4.Что мы при этом чувствуем&5.Причина возникновения ситуации&6.Тенденция ее развития&7.Точка зрения спрашивающего&8.Точка зрения других людей&9.То чего спрашивающий ждет или чего опасается&10.Перспективы и результаты',
               'один из самых известных и самых старинных раскладов карт Таро. Он наиболее универсален, то есть подходит для ответов на любые вопросы, особенно о том, как будут развиваться события, в чем причины происходящего, что ожидает человека или как возникла та или иная ситуация.',
               '✖️', 'AgACAgIAAxkBAAIFJGV9WIEm4JyT2Oj0-uOCWFoS9WeSAAKY1zEbD-DoS-NrIjnvauU9AQADAgADcwADMwQ'),
        (2, 'Круг Соломона', 'Circle of Solomon', '1.То, что вам предстоит понять&2.То, что невозможно изменить&3.Некое решение, которое вам придется принять&4.То, что вам предстоит сделать в будущем&5.То, чего делать, наоборот, не нужно или не придется&6.Финальная рекомендация колоды и совет на будущее,
                       'Круг царя Соломона – одно из магических изобретений, приписываемых этому правителю. Это гадание пользовалось (и пользуется до сих пор) большой популярностью во всех уголках мира, так как оно просто в использовании и дает исчерпывающие ответы на поставленные вопросы.',
                       '🔘', 'AgACAgIAAxkBAAIFJGV9WIEm4JyT2Oj0-uOCWFoS9WeSAAKY1zEbD-DoS-NrIjnvauU9AQADAgADcwADMwQ'),
        (3, 'Простой расклад (одна карта)', 'Simple layout (one card)', '1.Зависит от контекста гадания',
                       'Это расклад Таро на 1 карту с вопросами «Да или нет?», «Делать или не делать?», «Быть или не быть?», «Как поступить?». Он является одновременно и простым, и сложным. Один из самых распространенных раскладов',
                       '💳', 'AgACAgIAAxkBAAIFJGV9WIEm4JyT2Oj0-uOCWFoS9WeSAAKY1zEbD-DoS-NrIjnvauU9AQADAgADcwADMwQ'),
        (4, 'Универсальная линейная схема (три карты)', 'Universal linear circuit (three cards) ', '1.Прошлое&2.Настоящее&3.Будущее',
                       'Этот расклад представляет 3 периода времени - прошлое, настоящее и будущее',
                       '⏱', 'AgACAgIAAxkBAAIFJGV9WIEm4JyT2Oj0-uOCWFoS9WeSAAKY1zEbD-DoS-NrIjnvauU9AQADAgADcwADMwQ'),
        (5, 'Подкова', 'Horseshoe', '1.Влияния прошлого на настоящее&2.Текущая ситуация&3.Будущее развитие событий&4.То, как нужно поступить&5.Влияние среды на ситуацию&6.Трудности с которыми можно столкнуться&7.Конечный итог',
                       'Расклад «Подкова» подсказывает, как поступать в сложной ситуации. Он подробно описывает обстоятельства и указывает на вещи или людей, которые могут вам помочь. Делайте его каждый раз, когда не можете принять серьезное решение.',
                       '🧲', 'AgACAgIAAxkBAAIFJGV9WIEm4JyT2Oj0-uOCWFoS9WeSAAKY1zEbD-DoS-NrIjnvauU9AQADAgADcwADMwQ'),
        (6, 'Вокзал для двоих', 'Train station for two', '1.Мысли и восприятия мужчины относительно женщины&2.Чувства и эмоции мужчины к женщине&3.Действия предпринимаемые мужчиной относительно женщины&4.Мысли женщины о мужчине, как она его видит&5.Чувства женщины к мужчине, эмоциональное положение, её настрой относительно мужчины&6.То, что мужчина знает о женщине и то, что она хочет, чтобы он узнал о ней&7.Состояние и характеристика отношений на данный промежуток времени. Почему эти два человека общаются или же почему они вместе',
                       'Расклад из 7 карт открывает перспективы на знакомство с новым человеком, с которым Вы бы хотели построить отношения. Поможет выбрать правильную модель поведения и избежать досадных ошибок.',
                       '🚉', 'AgACAgIAAxkBAAIFJGV9WIEm4JyT2Oj0-uOCWFoS9WeSAAKY1zEbD-DoS-NrIjnvauU9AQADAgADcwADMwQ'),
        (7, 'Отношения под микроскопом', 'Relationships under the microscope', '1.Отношения на сегодняшний день&2.Что он чувствует?&3.Что он хочет?&4.Чего он боится?&5.Как он ко мне относится?&6.Что он будет делать?&7.Что мне делать?&8.Итог отношений&9.Перспективы',
                       'Расклад из 9 карт о любви между двумя людьми. Позволяет взглянуть на отношения свежим взглядом, увидеть невидимое и помогает определить свою позицию в отношениях.',
                       '🔬', 'AgACAgIAAxkBAAIFJGV9WIEm4JyT2Oj0-uOCWFoS9WeSAAKY1zEbD-DoS-NrIjnvauU9AQADAgADcwADMwQ'),
        (8, 'Два сердца', 'Two hearts', '1.Какие отношения между вами сейчасы&2.Совместимость с человеком&3.Ожидания кверента от союза с партнером&4.Ожидания объекта в отношении гадающего&5.Как будет развиваться союз в обозримом будущем&6.Что сделать для улучшения взаимоотношений&7.Ухудшится ли союз от событий извне&8.Посторонние влияния на развитие отношений',
                       'Расклад из 9 карт, для тех, чьи отношения начались совсем недавно и находятся в конфетно-букетном периоде',
                       '💕', 'AgACAgIAAxkBAAIFJGV9WIEm4JyT2Oj0-uOCWFoS9WeSAAKY1zEbD-DoS-NrIjnvauU9AQADAgADcwADMwQ'),
        (9, 'Карьера', 'Career', '1.Текущее положение дел&2.Возможности карьерного роста на данном месте&3.Что нужно делать чтобы достичь успеха&4.Что еще нужно сделать чтобы достичь успеха&5.Чему нужно уделить особое внимание&6.Чего нужно избегать&7.Чего еще нужно избегать&8.Будущее человека в профессиональной сфере',
                       'В этом загадочном раскладе на 8 карт таро, энергия профессионального пути раскрывается как тайна, рассеивающаяся в свете карт. Советы, представленные в этом раскладе, мерцают, как светлячки в ночи, указывая путь к преодолению трудностей',
                       '📈', 'AgACAgIAAxkBAAIFJGV9WIEm4JyT2Oj0-uOCWFoS9WeSAAKY1zEbD-DoS-NrIjnvauU9AQADAgADcwADMwQ'),
        (10, 'Работа и деньги', ' Work and money', '1.События прошлого, влияющие на настоящее&2.Как обстоит дело на работе с объективной точки зрения&3.Удовлетворенность кверента от выполнения служебных обязанностей&4.Выгода, которой можно достичь на текущем месте работы&5.Будут ли изменения в ближайшее время&6.Что поспособствует изменениям&7.Повлияют ли эти изменения на прибавку к зарплате&8.Что еще принесут перемены, как они отразятся на жизни гадающего',
                       'В этом таинственном раскладе из девяти карт таро, энергия труда и финансов ткается как поток магических символов. В этом чудесном танце карт таро раскрывают тайны трудового и финансового пути, словно предсказание, вписанное пером волшебника',
                       '💸', 'AgACAgIAAxkBAAIFJGV9WIEm4JyT2Oj0-uOCWFoS9WeSAAKY1zEbD-DoS-NrIjnvauU9AQADAgADcwADMwQ'),
        (11, 'Духовный рост', 'Spiritual progress', '1.Где вы в духовном цикле сейчас&2.Урок центрального цикла&3.Позитивные влияния&4.Негативные влияния&5.Совет',
                       'Карты Таро, раскладываемые для прозрения душевного роста, могут предоставить глубокий взгляд в мистическом измерении.Этот мистический расклад напоминает, что душевный рост – это постоянный процесс, наполненный тайнами и возможностью раскрытия собственного потенциала. Подними туманный покров и обнажится путь к душевному росту через карты Таро. ',
                       '👻', 'AgACAgIAAxkBAAIFJGV9WIEm4JyT2Oj0-uOCWFoS9WeSAAKY1zEbD-DoS-NrIjnvauU9AQADAgADcwADMwQ'),
        (12, 'Доктор Айболит', 'Dr. Bailony', '1.Субъективное самочувствие&2.Самочувствие кверента с объективной точки зрения&3.Кармические последствия ситуации&4.Наследственная передача болезни&5.Ошибки в ведении правильного образа жизни&6.Порча, сглаз или несчастный случай&7.Показано срочное хирургическое вмешательство&8.Нейтральное отношение к ситуации&9.Итог',
                       'Расклад "Доктор Айболит" в Таро - это загадочное объединение девяти карт, создающих мистическую картину заботы и исцеления. Под покровительством таинственных карт Таро раскрывается загадочная сцена, где девять карт образуют узор, олицетворяя мудрость и врачебные способности Доктора Айболита.',
                       '👨‍⚕️', 'AgACAgIAAxkBAAIFJGV9WIEm4JyT2Oj0-uOCWFoS9WeSAAKY1zEbD-DoS-NrIjnvauU9AQADAgADcwADMwQ'),
        (13, 'Мое здоровье', 'My health', '1.Объективное состояние организма и здоровья&2.Что способствует укреплению организма&3.Что ослабляет иммунитет&4.Факторы негативно воздействующие на здоровье в целом&5.Что нужно сделать для укрепления здоровья&6.Каково будет самочувствие в ближайшем будущем&7.Уточнение положения шесть&8.Завершение уточнения положения шесть',
                       'На картах таро развертывается мистический пейзаж здоровья через восемь карт. Этот таро-портрет воплощает мистический путь к полному физическому благополучию.',
                       '💊', 'AgACAgIAAxkBAAIFJGV9WIEm4JyT2Oj0-uOCWFoS9WeSAAKY1zEbD-DoS-NrIjnvauU9AQADAgADcwADMwQ'),
        (14, 'Важное решение', 'Important decision', '1.Сигнификатор проблемы&2.Уместно ли сейчас это решение?&3.Последствия принятного решения в ближайшем будущем&4.Факторы не принятые во внимание&5.Факторы, значимость которых была явно преувеличена&6.Какую пользу может для себя извлечь вопрошающий от принятия этого решения&7.Чем вопрошающему придется заплатить за полученную выгоду&8.Совет',
                       'В раскладе 8 карт Таро "Важное решение" проявляется загадочная симфония сил и судебных веяний. Судьба держит в своих руках ваши решения, и в каждой карте мерцает свечение ключа к великому разгадыванию ваших судебных узоров.',
                       '⚖️', 'AgACAgIAAxkBAAIFJGV9WIEm4JyT2Oj0-uOCWFoS9WeSAAKY1zEbD-DoS-NrIjnvauU9AQADAgADcwADMwQ'),
        (15, 'Квадрат Декарта', 'Descartes square', '1.Что хорошего случится, если я сделаю то, что задумано?&2.Что может случиться плохого, если я это сделаю?&3.Что хорошего в том, что бы этого не делать?&4.Что плохого может быть в том, что бы этого не делать?&5.Общее описание ситуации',
                       'Пятерка карт Таро, раскладываемая в форме Квадрата Декарта, раскрывает таинственные пути судьбы. В центре этого магического квадрата витает карта Судьбы, символизируя центральное влияние высших сил. В этом мистическом квадрате тайны раскрываются, а судьба расплетается в уникальном танце эзотерических сил.',
                       '⊡', 'AgACAgIAAxkBAAIFJGV9WIEm4JyT2Oj0-uOCWFoS9WeSAAKY1zEbD-DoS-NrIjnvauU9AQADAgADcwADMwQ'),
        (16 'Саморазвитие', 'Self-development', '1.Ваше прошлое "Я"&2.Ваше нынешнее "Я"&3.Ваше высшее "Я"&4.Вызовы&5.Семейные паттерны&6.Личные проблемы&7.Совесть&8.Желание&9.Урок&10.Совет',
                       'Расклад "Саморазвитие" из 10 карт Таро представляет собой путеводную картину, вдохновляющую на личный и духовный рост. Этот расклад напоминает о постоянном стремлении к самопознанию и духовному росту, воплощая в себе магию личного развития.',
                       '🏃', 'AgACAgIAAxkBAAIFJGV9WIEm4JyT2Oj0-uOCWFoS9WeSAAKY1zEbD-DoS-NrIjnvauU9AQADAgADcwADMwQ'),
        (17, 'Солнце', 'The sun', '1.Мое "Я", то, что является важнейшим для меня&2.Уровень агрессии&3.В эту сторону стоит направить свои честолюбивые устремления&4.Где должна реализоваться моя креативность?&5.На каких действиях мне нужно сосредоточиться в ближайшее время?&6.Что мне нужно запланировать на потом?&7.Сила воли&8.Витальность и радость жизни&9.Честь, чувство собственного достоинства&10.Направление развития, в котором должна двигаться моя личность',
                       'Солнце в Таро, представленное десятью картами, раскрывает свою мистическую сущность как источник беспрекословного света и жизненной энергии. Этот расклад предвосхищает благосклонное воздействие светила на все аспекты человеческой жизни. От начала до конца, отрывая завесы тайны, Солнце восполняет духовные потери и наполняет сердца вдохновением. Каждая карта открывает новый уровень освещения, обрушивая свет своей благословенной энергии на пути истинного понимания и внутреннего прозрения.',
                       '☀️', 'AgACAgIAAxkBAAIFJGV9WIEm4JyT2Oj0-uOCWFoS9WeSAAKY1zEbD-DoS-NrIjnvauU9AQADAgADcwADMwQ');
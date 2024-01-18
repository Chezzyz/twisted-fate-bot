DROP TABLE IF EXISTS kabbalistic_numbers;
CREATE TABLE kabbalistic_numbers
(
    num_value          smallint NOT NULL,
    letters            text,
    meaning            text     NOT NULL,
    divination_meaning text,
    PRIMARY KEY (num_value),
    UNIQUE (num_value)
);
INSERT INTO kabbalistic_numbers(num_value, meaning, divination_meaning, letters)
VALUES (0, '', '', 'Ы, ы, Ъ, ъ, Ь, ь'),
       (1, 'Честолюбие, жадность, грубость', 'Делу благоприятствует само Небо', 'А, а, A, a'),
       (2, 'Разрушение, гибельное последствие', 'Потребуется намного больше усилий, чем казалось', 'Б, б, B, b'),
       (3, 'Религиозность, стремление к лучшему', 'Будьте готовы к потерям на этом пути', 'В, в, C, c'),
       (4, 'Власть, нерадение, ум', 'Дело предстоит сложное, но в конце всё получится', 'Г, г, D, d'),
       (5, 'Храбрость, честность, благополучие', 'Бросьте это, вас может ждать трагедия', 'Д, д, E, e'),
       (6, 'Труд, свободолюбие, успех', 'Сами вы не сможете, вам понадобится помощь со стороны',
        'Е, е, Ё, ё, Э, э, F, f'),
       (7, 'Бедность, преступление, недалекость', 'Полная неудача, а возможно и разгром', 'Ж, ж, G, g'),
       (8, 'Величие, кротость, справедливость', 'Не спешите, это дело требует аккуратности', 'З, з, H, h'),
       (9, 'Мудрость, свободомыслие, почет', 'Будьте внимательны — впереди большие проблемы', 'И, и, Й, й, I, i'),
       (10, 'Добродушие, справедливость, красота души', '', 'К, к, J, j'),
       (11, 'Преступность, противозаконие, вздорность', '', ''),
       (12, 'Безбожие, неверие, вольность', '', ''),
       (13, 'Благость, победа тьмы, бессмертие', '', ''),
       (14, 'Жертва, порок', '', ''),
       (15, 'Проницательность, вера в Бога', '', ''),
       (16, 'Благополучие, любовь, семья', '', ''),
       (17, 'Несчастья, порочность, зло', '', ''),
       (18, 'Сила воли', '', ''),
       (19, 'Бесхарактерность, нерешительность, робость', '', ''),
       (20, 'Печаль, строгость, неуспех', '', 'Л, л, K, k'),
       (21, 'Любовь к ближним, симпатия', '', ''),
       (22, 'Мудрость, глубина, гениальность', '', ''),
       (23, 'Бич Божий, преступление, наказание', '', ''),
       (24, 'Хорошее стремление, добродетель', '', ''),
       (25, 'Знатность, слава', '', ''),
       (26, 'Полезный труд, добро, душевность', '', ''),
       (27, 'Мужество, сила воли', '', ''),
       (28, 'Удача в любви, счастье, богатство', '', ''),
       (29, 'Злой замысел, ничтожество, себялюбие', '', ''),
       (30, 'Удачный брак, успех, известность', '', 'М, м, L, l'),
       (31, 'Добродетель, справедливость', '', ''),
       (32, 'Верность, чистота души', '', ''),
       (33, 'Величие, красота', '', ''),
       (34, 'Болезнь души, страдание, немощь', '', ''),
       (35, 'Стремление к высшему', '', ''),
       (36, 'Выдающаяся способность, величие души', '', ''),
       (37, 'Кротость, семейное счастье', '', ''),
       (38, 'Неисполнение, несовершенство души', '', ''),
       (39, 'Бедность, слабость ума', '', ''),
       (40, 'Довольствие, настойчивость', '', 'Н, н, M, m'),
       (41, 'Томление души, печаль, неуспех', '', ''),
       (42, 'Путешествие, трудоспособность', '', ''),
       (43, 'Обрядность, недалекость', '', ''),
       (44, 'Энергичность, величие, успех', '', ''),
       (45, 'Заточение, потеря здоровья', '', ''),
       (46, 'Богатство, общественное признание', '', ''),
       (47, 'Долголетие, благополучие', '', ''),
       (48, 'Суд, приговор, наказание', '', ''),
       (49, 'Ничтожность, корысть', '', ''),
       (50, 'Освобождение, забвение, свобода', '', 'О, о, N, n'),
       (60, 'Одиночество, потеря лучшего', '', 'П, п, O, o'),
       (70, 'Наука, величие, светлость ума', '', 'Р, р, P, p'),
       (73, 'Дарование, стремление к наукам', '', ''),
       (75, 'Способность', '', ''),
       (77, 'Раскаяние, прощение', '', ''),
       (80, 'Великое несчастье, война', '', 'С, с, Q, q'),
       (87, 'Верование', '', ''),
       (90, 'Заблуждение, ослепление, неудача', '', 'Т, т, R, r'),
       (100, 'Политическая деятельность, стремление к выгодам', '', 'У, у, S, s'),
       (120, 'Патриотизм, религиозность', '', ''),
       (150, 'Похвала, победа', '', ''),
       (200, 'Хладнокровие, слабохарактерность', '', 'Ф, ф, T, t'),
       (300, 'Философия, глубина мысли', '', 'Х, х, U, u'),
       (315, 'Зло, хамство, вред', '', ''),
       (318, 'Добродетель, миролюбие', '', ''),
       (350, 'Справедливость, крепость', '', ''),
       (360, 'Общественная деятельность', '', ''),
       (365, 'Путешествие, усталость, неудача', '', ''),
       (400, 'Высшая наука, проницательность', '', 'Ц, ц, V, v'),
       (409, 'Богословие, начитанность, церковность', '', ''),
       (500, 'Любвеобилие, простота, знатность', '', 'Ч, ч, W, w'),
       (600, 'Успех, победа', '', 'Ш, ш, X, x'),
       (666, 'Убийство, зло, вражда', '', ''),
       (700, 'Владычество, гордость', '', 'Щ, щ, Y, y'),
       (800, 'Катастрофа, государство, измена', '', 'Ю, ю, Z, z'),
       (900, 'Война, бедствие, жертвы', '', 'Я, я'),
       (1000, 'Поэзия, любовь, самостоятельность', '', ''),
       (1095, 'Скромность, кротость', '', ''),
       (1260, 'Испытание, гнет, мучение', '', ''),
       (1390, 'Опасность', '', '');
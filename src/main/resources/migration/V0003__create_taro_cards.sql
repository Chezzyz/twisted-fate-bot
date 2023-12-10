DROP TABLE IF EXISTS taro_cards;
CREATE TABLE taro_cards
(
    id          SERIAL       NOT NULL,
    card_number INTEGER      NOT NULL,
    rus_name    VARCHAR(255) NOT NULL,
    eng_name    VARCHAR(255) NOT NULL,
    image_path  VARCHAR(255) NOT NULL,
    description TEXT,
    features    TEXT,
    PRIMARY KEY (id),
    UNIQUE (id)
);
INSERT INTO taro_cards(card_number, rus_name, eng_name, image_path, description, features)
VALUES (0, 'Шут🤡', 'The Fool🤡', 'classpath:images/taro-cards/Fool.jpg',
        'Шут означает новое начало. Он пробуждает в нас любопытного ребенка, заставляет сделать что-то такое, от чего захватывает дух, выйти из зоны комфорта и проверить предел собственных возможностей. Шут не зависит от общественных условностей, стереотипов и штампов',
        'внутренняя свобода, жизнь здесь и сейчас, любопытство, спонтанность'),
       (1, 'Маг🔮', 'The Magician🔮', 'classpath:images/taro-cards/Magician.jpg',
        'При толковании карт Таро Маг является символом творческих способностей, которые нужно приложить для достижения собственных целей, энтузиазм и готовность действовать. У Мага нет сомнений в том, что он делает, он тренирует интуицию. При появлении Мага в раскладе речь также идет о развитии творческого потенциала и умении быть счастливым',
        'самоконтроль, уверенность в себе, креатив, жажда действия, смелости, оригинальности, действие, неформальность, осведомленность, эгоцентризм, высокомерие, самодовольство'),
       (2, 'Верховная Жрица🧝‍♀️', 'The High Priestess🧝‍♀️', 'classpath:images/taro-cards/Priestess.jpg',
        'Верховная жрица символизирует гармонию противоположностей и умение вести переговоры, находить решение, которое устроит всех',
        'готовность принимать решения, забота, новые идеи и задачи, гармония интуиции и разума'),
       (3, 'Императрица👸', 'The Empress👸', 'classpath:images/taro-cards/Empress.jpg',
        'Эта карта Таро говорит о том, что мы должны принять свой выбор и изменить образ жизни или поведение. Императрица символизирует отдачу и принятие, новое начало и отказ от устаревших убеждений. Эта карта говорит о том, что вы шаг за шагом приближаетесь к своим целям.',
        'развитие, ответственность за свою жизнь, привязанность к матери, материнство, осознание собственных целей'),
       (4, 'Император🤴', 'The Emperor🤴', 'classpath:images/taro-cards/Emperor.jpg',
        'Император сочетает в себе уверенность в себе с ответственностью и призывает нас жить полной жизнью и использовать собственный потенциал по максимуму. Император воплощает в себе потенциал для новых возможностей. Он управляет своей империей в соответствии со сводом законов и правил, созданных им же, и несет ответственность за свои ошибки.',
        'сила, уверенность в себе, стабильность, порядок, самопознание, контроль, воля к победе'),
       (5, 'Иерофант🛐⛪', 'The Hierophant🛐⛪', 'classpath:images/taro-cards/Hierophant.jpg',
        'Иерофант в списке карт Таро в классической колоде — синоним совести. Это также символ властей, организаций и учреждений. Иерофант демонстрирует традиционный взгляд на вещи, а это означает, что тем, кто ищет совета, стоит ориентироваться на признанные ценности. Иерофант может говорить об улучшении жилищных условий и окупаемости вложений.',
        'действия по зову сердца и велению души, связь аналитического ума и интуиции, верования, иерархии, союз противоположностей'),
       (6, 'Влюблённые❤️', 'The Lovers❤️', 'classpath:images/taro-cards/Lovers.jpg',
        'Если в раскладе появляются Влюбленные, это сигнал о том, что нужно доверять своим чувствам и сердцу. Эта карта мотивирует адаптироваться к естественному порядку вещей во всех видах связей, в том числе в отношениях. Не надо расценивать эту карту как любовный роман со счастливым концом. Скорее, это совет решить, по какому маршруту вы будете двигаться дальше.',
        'Доверие, безопасность, оптимизм, союз противоположностей, тоска по второй половине, проблемы с решением, гармония, совершенство, ложь, обман, болезненные прозрения'),
       (7, 'Колесница🛴', 'The Chariot🛴', 'classpath:images/taro-cards/Chariot.jpg',
        'Если Колесница появляется в раскладе, это сигнал о том, что успех не за горами, если мы опираемся на интуицию. Проект, который находится на стадии планирования, будет удачным. Также карта сигнализирует о том, что нужно сохранять уверенность, чтобы двигаться в правильном направлении. Колесница в Таро также символизирует риск принятия решений, давления и бегства.',
        'прорыв, триумф, движение'),
       (8, 'Сила💪', 'Strength💪', 'classpath:images/taro-cards/Strength.jpg',
        'Карта также показывает, что у каждого человека есть и животные инстинкты, которые нужно принять и взять под контроль. Сила велит мужественно смотреть в глаза своим страхам и действовать решительно. Эта карта Таро — символ оптимизма и страсти, которая должна быть хорошо дозирована.',
        'уверенность, решимость, страсть, оптимизм, движущая сила, драма, упадок, шаткость'),
       (9, 'Отшельник🧙‍♂️', 'The Hermit🧙‍♂️', 'classpath:images/taro-cards/Hermit.jpg',
        'Эта карта Таро в раскладе показывает нам, что все, что нам нужно, спрятано внутри, а не снаружи. Отшельник допускает множество вариаций трактования – удалось ли ему достичь цели или он изначально был не прав? Так или иначе, этот старший аркан можно расценивать как рекомендацию сосредоточиться на поиске истинного я, не распыляясь на мелочи.',
        'зрелость, мудрость, рассудительность, осознание приоритетов, самоанализ'),
       (10, 'Колесо Фортуны🎡', 'Wheel of Fortune🎡', 'classpath:images/taro-cards/Wheel.jpg',
        'Колесо фортуны говорит о том, что мы не потерпим неудачу, если помним о своей цели и стараемся извлечь максимум из имеющейся ситуации. Ничего в жизни не остается навечно, но всегда есть надежда, что все будет хорошо. Карта Таро Колесо фортуны также включает в себя кармические аспекты, которые дают понять, что определенные жизненные уроки необходимо пережить, чтобы могло происходить дальнейшее развитие.',
        'предстоящие изменения, активность, возможности, оптимизм, конец определенного периода в жизни, разрешение ситуации, новое начало, паралич, фатализм, нестабильность'),
       (11, 'Справедливость⚖️', 'Justice⚖️', 'classpath:images/taro-cards/Justice.jpg',
        'Справедливость говорит о том, что те, кто несет ответственность за свои решения, не обвиняя других, увеличивают свои шансы на успех. Это аркан призывает к справедливости и сохранению нейтралитета. Может означать также судебное заседание или нерешенный вопрос.',
        'осуждение, разрешение сомнений, уравновешенность, беспристрастность, ответственность за свои действия, трансформация, созревание, предубеждение'),
       (12, 'Повешенный🙃', 'The Hanged Man🙃', 'classpath:images/taro-cards/Hanged.jpg',
        'Повешенный – символ необходимости разрешения, казалось бы, безвыходной ситуации. Если мы признаем, что все наши усилия ни к чему не приводят, то осознание этого может указать нам правильный путь.',
        'отказ от неправильного образа жизни, духовная зрелость, духовное пробуждение, разворот, остановка, независимость, застой'),
       (13, 'Смерть💀', 'Death💀', 'classpath:images/taro-cards/Death.jpg',
        'Смерть в Таро символизирует болезненную утрату. Старое должно пройти, и тогда новое сможет расцвести. Данный аркан — это повод расстаться с тем, что уже отмерло (в переносном смысле слова, естественно) и освободить место для чего-то нового. Будет непросто, но затем наступит ясность. Самое главное – Смерть не означает смерть в прямом значении, она символизирует окончание чего-то, например, отрезка жизненного пути.',
        'конец этапа, отказ от устаревших ценностей и привычек, страх нового начала, необратимость изменения'),
       (14, 'Умеренность😐', 'Temperance😐', 'classpath:images/taro-cards/Temperance.jpg',
        'Эта карта Таро говорит о том, что мы можем достичь внутреннего баланса, если только будем помнить про интуицию и чувство меры одновременно. Если вы берете столько же, сколько отдаете, и живете в гармонии, то беспокоиться не о чем. Этот аркан означает безмятежность, глубокое удовлетворение и хорошее здоровье.',
        'равенство противоположных начал, правильный микс, дипломатия, внутренняя сила, ангел-хранитель'),
       (15, 'Дьявол😈', 'The Devil😈', 'classpath:images/taro-cards/Devil.jpg',
        'В картах Таро Дьявол не считается символом зла, он дает нам понять, что мы должны освободиться от устаревших взглядов на жизнь и убеждений. Дьявол дает нам шанс самостоятельно формировать свою жизнь. Темная сторона дьявола в Таро отражает наше эго, которое слишком сильно привязано к материальным благам. Препятствия на пути могут быть также связаны с двуличием и лицемерием.',
        'побег из тюрьмы с предрассудками, несвобода из-за иллюзий, осознание ответственности, соблазнение, двуличие, эмоциональная зависимость'),
       (16, 'Башня🗼', 'The Tower🗼', 'classpath:images/taro-cards/Tower.jpg',
        'Благодаря своему положению между Звездой и Дьяволом, Башня символизирует переход от высокомерия к смирению. И напоминает о том, что однажды вроде бы гарантированно успешные и заранее просчитанные планы могут рухнуть, а все, что старательно подавлялось в душе, вырвется наружу.',
        'прорыв, освобождение, разрушение'),
       (17, 'Звезда🌟', 'The Star🌟', 'classpath:images/taro-cards/Star.jpg',
        'Звезда символизирует положительные сдвиги и укрепление психического здоровья, психотравмы исцеляются с помощью бодрящей энергии, в которой растворяются страхи. Счастливая карта сулит успех в наших начинаниях.',
        'творчество, дальновидность, счастье, дух созидания, расслабление, исцеление, душевное здоровье, ненадежность, лихорадочный темп, сомнения'),
       (18, 'Луна🌚', 'The Moon🌚', 'classpath:images/taro-cards/Moon.jpg',
        'Луна в Таро делает бессознательное более реальным и пробуждает глубоко скрытые ощущения, заставляет интуицию работать. Эта же карта Таро символизирует наши страхи, с которыми мы должны мужественно противостоять, чтобы успешно их преодолеть. Мы можем прочно закрепить наше представление о себе и наши цели. Луна также является символом перехода от бодрствования к сну.',
        'желания, подсознание, внутренний голос, ясность, глубина, обман, опасность, страх быть оставленным'),
       (19, 'Солнце☀️', 'The Sun☀️', 'classpath:images/taro-cards/Sun.jpg',
        'Солнце символизирует энтузиазм, силу и жизнерадостность. Аркан олицетворяет креативность, базовое доверие и позитивное мышление. Приятная карта Таро, она генерирует в нас свежую энергию, сигнализирует о позитивных изменениях в жизни, которые заставляют изменить стиль мышления.',
        'успех, правда, исцеление, бодрость, возрождение, уверенность в себе, тепло, свободное развитие, утверждение жизни, беспочвенная подозрительность, наивность, отрицание истины'),
       (20, 'Суд👨‍⚖️', 'Judgement👨‍⚖️', 'classpath:images/taro-cards/Judgement.jpg',
        'Карта Таро Суд не подразумевает Судный день. Скорее, символизирует пронзительно громкий сигнал к активной жизни. Вы можете начать новый этап жизни, целиком и полностью осознавая, что именно происходит.',
        'пробуждение внутреннего голоса, исцеление, освобождение, самообман, непринятие изменений'),
       (21, 'Мир🌎', 'The World🌎', 'classpath:images/taro-cards/World.jpg',
        'Мир символизирует свободу разума и зрелость сознания. Мир является символом радости, гармонии и самореализации. Одновременно с этим он олицетворяет конец пути , время извлекать уроки из личного опыта, своих взлетов и падений.',
        'единение с природой и людьми, самопознание, духовная зрелость, совершенство, путешествия, прогресс, определенный успех'),
-- Карты Кубков
       (22, 'Туз Кубков🏆', 'Ace of Cups🏆', 'classpath:images/taro-cards/Cups01.jpg',
        'Туз Кубков – это знак внутренней энергии, открытости души ко всему новому, душевной гармонии и комфорта. Одним словом можно сказать, что это карта блаженства. Выпадающий в раскладе такой Аркан указывает на то, что счастье стоит у дверей гадающего. Это светлый луч, появляющийся в темной душе, он выдворяет оттуда всю печаль, уныние, злость, страхи.',
        'открытия, подарок, дар судьбы, влюбленность'),
       (23, '2 Кубков🏆', 'Two of Cups🏆', 'classpath:images/taro-cards/Cups02.jpg',
        'Двойка кубков свидетельствует о взаимной симпатии, перспективных связях, успешном сотрудничестве, мире, который наконец-то случается после длительных ссор и размолвок. Обязательно в жизни человека произойдут радостные события и встречи. Карту считают положительной, так как она несет становление отношений, в которых могут быть задействованы как давние знакомые, так и новый круг общения.',
        'дружба, любовь, примирение, рождение ребенка'),
       (24, '3 Кубков🏆', 'Three of Cups🏆', 'classpath:images/taro-cards/Cups03.jpg',
        'Тройка кубков символизирует, как правило, время радости, праздника. В любовных и дружеских отношениях будет полное взаимопонимание между партнерами. Все запланированные дела увенчаются успехом. Аркан дает подсказку гадающему человеку, что сейчас наступает благоприятное время, чтобы восстановить отношения со старыми друзьями, родственниками, ведь наступает светлая полоса в жизни.',
        'успех, дело, любовь, дружба, радость'),
       (25, '4 Кубков🏆', 'Four of Cups🏆', 'classpath:images/taro-cards/Cups04.jpg',
        'Человек, которому выпала в раскладе четверка кубков, упускает большие возможности в своей жизни. Это происходит по причине того, что он слишком эгоистичен, заносчив, горд, считая себя излишне умным. Он обижен на многих людей, недоволен собой, что выражается в постоянном процессе самоанализа. Это создает большое количество комплексов и страхов.',
        'обида, неуверенность, апатия, замкнутость'),
       (26, '5 Кубков🏆', 'Five of Cups🏆', 'classpath:images/taro-cards/Cups05.jpg',
        'Пятерка кубков говорит о разочаровании, неожиданных неприятностях, меланхолии, печали, боли, скорби об утраченном. Возможно, у человека произойдет состояние сожаления из-за того, что его мечты и желания не осуществились, хотя он так на это рассчитывал.',
        'печаль, провал, проблемы в семье, выкидыш'),
       (27, '6 Кубков🏆', 'Six of Cups🏆', 'classpath:images/taro-cards/Cups06.jpg',
        'Шестерка кубков означает внутреннюю гармонию, великодушие и щедрость. В действительности, карта отвечает за позитивное проявление. Можно считать, что все невзгоды уже в прошлом, а теперь только благополучие. Как только трудности отступили, человек вновь начинает мечтать и строить планы, довольствуется спокойствием и успехами своих трудов.',
        'уют, покой, старый друг, дети, годовщина'),
       (28, '7 Кубков🏆', 'Seven of Cups🏆', 'classpath:images/taro-cards/Cups07.jpg',
        'Основное значение семерки кубков заключается в том, что каждый выбор, над которым гадающий раздумывает, уже предопределен судьбой, то есть сам факт выбора – иллюзия, обман. Кроме того, карта может трактоваться и как ошибочный выбор, который не несет ничего хорошего в себе. Указывает Аркан в некоторых случаях и на то, что вариантов слишком много, сложно определиться, прийти к единственно верному решению.',
        'трудности, ошибочный выбор, творческий прорыв'),
       (29, '8 Кубков🏆', 'Eight of Cups🏆', 'classpath:images/taro-cards/Cups08.jpg',
        'Выпадает восьмерка кубков, как правило, человеку, который ищет по жизни все больше новых благ, при этом они все у него перед носом. Часто карта символизирует отказ от мирского вследствие разочарования, переходного периода. В итоге начинается поиск духовного пути.',
        'разочарование, неуверенность, скромность, честность'),
       (30, '9 Кубков🏆', 'Nine of Cups🏆', 'classpath:images/taro-cards/Cups09.jpg',
        'Если в раскладе выпадает девятка кубков, то в будущем ожидает успех в материальном плане, хороший достаток. Наступает светлая полоса, эмоциональное удовольствие, а также интеллектуальное. Здоровью не угрожают никакие проблемы. Любые цели, которые намечаются, могут воплотиться в жизнь.',
        'достаток, успех, излишества, праздник, брак'),
       (31, '10 Кубков🏆', 'Ten of Cups🏆', 'classpath:images/taro-cards/Cups10.jpg',
        'Десятка кубков символизирует счастливую и гармоничную семейную жизнь. Счастье будет продолжительным, принесет массу положительных эмоций. Человек, делающий расклад, добьется многого в духовной сфере, отчего испытает неописуемую радость. Все дела будут благополучно завершены. Карта может предзнаменовать начало какого-то нового этапа в жизни, что принесет с собой душевное спокойствие и гармонию.',
        'счастье, семья, душевное спокойствие, начало'),
       (32, 'Паж Кубков🏆', 'Page of Cups🏆', 'classpath:images/taro-cards/Cups11.jpg',
        'Паж кубков наделен тихой радостью, романтикой, мечтой и грезами. Карта несет эмоциональную нагрузку и является символом любви. При раскладе стоит ожидать любовного послания, известия о свадьбе, возможно, придет информация о беременности или рождении ребенка.',
        'хорошие новости, импульс, шанс, толчок'),
       (33, 'Рыцарь Кубков🏆', 'Knight of Cups🏆', 'classpath:images/taro-cards/Cups12.jpg',
        'Какими качествами обладает рыцарь? Конечно же, романтичность, сердечность, целеустремленность. Если расклад делается на характер человека, именно эти качества будут описывать его натуру. Он верен своим принципам и идеалам, всегда следует за мечтой, очень чуток и тактичен.',
        'внушение, скрытое намерение, настроение, розовые очки'),
       (34, 'Королева Кубков🏆', 'Queen of Cups🏆', 'classpath:images/taro-cards/Cups13.jpg',
        'Королева Кубков символизирует успех в задуманных делах при помощи гениальной интуиции. В жизни человека, которому выпала эта карта, вообще есть ощущение постоянного предчувствия, словно дар ясновидения.',
        'успех, покой, сдержанность, воображение'),
       (35, 'Король Кубков🏆', 'King of Cups🏆', 'classpath:images/taro-cards/Cups14.jpg',
        'Выпадает Король Кубков как знак привязанности к родным местам и собственному уютному дому. Кризисные моменты начинают заканчиваться, препятствия проходят мимо вас стороной, а впереди только широкая светлая полоса и благоприятные разрешения всех трудностей. Отношения в браке станут очень крепкими, наладится между супругами прочная эмоциональная связь и доверие.',
        'эмпатия, любезность, воображение, элегантность'),

-- Карты Пентаклей
       (30, 'Туз Пентаклей💰', 'Ace of Pentacles💰', 'classpath:images/taro-cards/Pents01.jpg',
        'В скором времени ожидается этап процветания в жизни. Возможно, начнутся новые проекты, которые увенчаются успехом. Вследствие этого, гадающий почувствует моральное удовлетворение. Туз пентаклей – это всегда личностный рост. Есть вероятность, что вы начнете заниматься чем-то духовным либо получите повышение на работе.',
        'процветание, независимость, удача, похвала, личные достижения'),
       (36, '2 Пентаклей💰', 'Two of Pentacles💰', 'classpath:images/taro-cards/Pents02.jpg',
        'Гадающего человека ожидают хорошие перемены. Он будет жить в гармонии с собой, улучшит свое материальное благосостояние, получит разнообразие и полезный взаимообмен. Известие или какая-нибудь новость, которую он получит в ближайшее время, не оставит его в покое, взбудоражит и взволнует его в позитивном плане. Это свидетельствует о его заметной динамике вперед.',
        'хаотичность, эмоции, ловкость, безразличие, поездка'),
       (37, '3 Пентаклей💰', 'Three of Pentacles💰', 'classpath:images/taro-cards/Pents03.jpg',
        'Тройка пентаклей символизирует эволюцию, постепенный прогресс. Ее появление не обещает ошеломляющих результатов, шумных побед и таких же поражений – оно свидетельствует о том, что цель будет достигнута в результате регулярных усилий, постепенно.',
        'решающий шаг, помощь, уверенность, удовольствие'),
       (38, '4 Пентаклей💰', 'Four of Pentacles💰', 'classpath:images/taro-cards/Pents04.jpg',
        'Четверка пентаклей является символом границ – правильного, полезного, допустимого, возможного. Эта карта в прямом положении указывает на такие свойства характера, как честность, практичность, деловая хватка, умение держать слово, приверженность закону и установленным нормам поведения.',
        'алчность, скупость, подарок, наследство'),
       (39, '5 Пентаклей💰', 'Five of Pentacles💰', 'classpath:images/taro-cards/Pents05.jpg',
        'Чаще всего пятерка пентаклей выступает символом беспокойства. Это может быть мотивированная тревога – деловые сложности, слишком большие нагрузки, большая ответственность, риск потерять что-либо ценное, а также беспричинные страхи – фобии, депрессию.',
        'неприятности, потери, невезение, нет почвы под ногами'),
       (40, '6 Пентаклей💰', 'Six of Pentacles💰', 'classpath:images/taro-cards/Pents06.jpg',
        'В раскладе на характер гадающего, шестерка пентаклей указывает на гордого и самовлюбленного человека, который берет на себя слишком много. Он считает себя владыкой, хотя в его руках власть не так уж и велика. Часто это просто жулик, мошенник, который пользуется чужой славой.',
        'благотворительность, помощь, гордливость, наставничество'),
       (41, '7 Пентаклей💰', 'Seven of Pentacles💰', 'classpath:images/taro-cards/Pents07.jpg',
        'Важный критерий успеха в деле – быть терпеливым. В течение года ваш труд обязательно вознаградят достойной оплатой. Не переживайте за то, что усилия окажутся напрасными, даже если возникает подобное предчувствие – оно ложное.',
        'терпение, медленный рост, беременность, богатство'),
       (42, '8 Пентаклей💰', 'Eight of Pentacles💰', 'classpath:images/taro-cards/Pents08.jpg',
        'Восьмерка пентаклей символизирует человека, который трудится на благо других людей. Он создает ценности как материальные, так и духовные. Этот человек свободен и успешен, но только лишь в том случае, если не ищет личную выгоду.',
        'успех, работа, занятость, удача'),
       (43, '9 Пентаклей💰', 'Nine of Pentacles💰', 'classpath:images/taro-cards/Pents09.jpg',
        'Основное значение карты - завершение долгосрочной работы. Однако окончание не делает человека счастливым. Гадающий может нуждаться в совете, но не знает к кому обратиться за ним. Карта подсказывает, что совет следует просить у мудрой женщины в годах.',
        'завершение работы, успех, труд, любовь к природе'),
       (44, '10 Пентаклей💰', 'Ten of Pentacles💰', 'classpath:images/taro-cards/Pents10.jpg',
        'Карта характеризует гадающего как мудрого человека, который обрел покой. Длительное дело увенчалось успешным разрешением. В семейных делах полная стабильность, отличные отношения между детьми и родителями, старшим и младшим поколением.',
        'мудрость, успех, стабильность, дом'),
       (45, 'Паж Пентаклей💰', 'Page of Pentacles💰', 'classpath:images/taro-cards/Pents11.jpg',
        'С картой паж пентаклей, человек реализует себя в жизни. К делу он подходит с творческим подходом, ведет себя чувственно и естественно. Благодаря этому человек развивается и растет в профессиональном плане. Карта может свидетельствовать о будущем, которое в скором времени ожидает гадающего человека.',
        'шанс, усердие, изучение, доброта, решительность'),
       (46, 'Рыцарь Пентаклей💰', 'Knight of Pentacles💰', 'classpath:images/taro-cards/Pents12.jpg',
        'С картой рыцарь пентаклей, человек ведет себя энергично и напористо. Он умеет концентрировать свое внимание на конкретной цели и даже в чем-то проявляет инициативу. Все эти цели в основном реалистичные и прагматичные. Это тот период жизни, когда человек находится в разгаре своих замыслов и планов. Притом он все делает последовательно и терпеливо, и неудивительно, что добивается хороших результатов.',
        'надежность, удача, терпение, выносливость'),
       (47, 'Королева Пентаклей💰', 'Queen of Pentacles💰', 'classpath:images/taro-cards/Pents13.jpg',
        'Королева пентаклей говорит о зрелости, опытности, здравомыслии, выносливости, безмятежности, плодородии. А значит, карта имеет позитивную и достойную сторону, и несет положительные моменты.',
        'доброта, трудолюбие, сердечность, верность'),
       (48, 'Король Пентаклей💰', 'King of Pentacles💰', 'classpath:images/taro-cards/Pents14.jpg',
        'В раскладе на характер трактуется карта как обозначение умной и дружелюбной личности. Он состоялся в жизни, многого достиг, у него богатый жизненный опыт. Такой человек мудр и храбр, решителен даже в самых сложных ситуациях. Чтобы достичь целей придется быть более практичным и настойчивым, просто так успех не придет. Успех в делах будет только при том условии, если вы сосредоточите на этом все ваше внимание, личные заботы перестанут интересовать.',
        'инстинкт, ум, хорошее чутье, терпеливость'),

-- Карты Мечей
       (40, 'Туз Мечей⚔️', 'Ace of Swords⚔️', 'classpath:images/taro-cards/Swords01.jpg',
        'Туз мечей – аркан триумфа и победы. Все сомнения оставляйте позади. Внесите во все сферы своей жизни ясность, так жить станет намного легче и веселее, не придется каждодневно ломать голову над всякими мелочами. Можно посвятить это время чему-то большему.',
        'решение проблемы, завоевание, свобода, решительность'),
       (49, '2 Мечей⚔️', 'Two of Swords⚔️', 'classpath:images/taro-cards/Swords02.jpg',
        'Двойка мечей - это знак того, что человек в силу каких-то причин закрылся от окружающих. Он хочет, чтобы его никто не трогал, в его проблемы никто не вмешивался. Окружающие в данный период раздражают.',
        'сдерживание эмоций, дружба, любовь, стойкость'),
       (50, '3 Мечей⚔️', 'Three of Swords⚔️', 'classpath:images/taro-cards/Swords03.jpg',
        'Тройка мечей символизирует боль, слёзы, горе, потерю близкого человека, тревоги, разочарования, что может вызвать сильную депрессию. Возможно, что гадающего человека бросили, и он находится в состоянии апатии.',
        'разбитое сердце, конфликт, разрыв, неприятности'),
       (51, '4 Мечей⚔️', 'Four of Swords⚔️', 'classpath:images/taro-cards/Swords04.jpg',
        'Четвёрка мечей указывает на то, что гадающий человек отгородился от окружающих. Ему не хочется никого видеть, все валится из рук, в делах застой. Главное не уходить в себя, ведь это может обернуться глубокими душевными расстройствами. Надо попросить помощи у тех, кто рядом, и она будет оказана. Стабилизировать душевное состояние поможет посещение святых мест.',
        'успокоение, застой, путешествие, самосозерцание'),
       (52, '5 Мечей⚔️', 'Five of Swords⚔️', 'classpath:images/taro-cards/Swords05.jpg',
        'Пятерка мечей несёт крайне негативное значение. Основные значения: тревога, подлость, страх, унижение, потеря, крах планов. У человека в жизни - чёрная полоса, ему не везёт ни в чём. Стрессы, слёзы, конфликты способствуют тому, что ему кажется, что просвета в улучшении ситуации никогда не будет. Он в отчаянии, ему кажется, что весь мир вступил с ним в противоборство.',
        'позор, неудача, потеря, трусость'),
       (53, '6 Мечей⚔️', 'Six of Swords⚔️', 'classpath:images/taro-cards/Swords06.jpg',
        'Шестерка мечей говорит о том, что наступило время перемен. Гадающий человек хочет изменить свою жизнь, но боится всего нового, так как является консерватором по натуре. Придётся заниматься какими-то хлопотами.',
        'перемены, любопытство, шанс на удачу, путешествие'),
       (54, '7 Мечей⚔️', 'Seven of Swords⚔️', 'classpath:images/taro-cards/Swords07.jpg',
        'Семерка мечей имеет много значений, и в этом противоречивость данного аркана. Проблемы и сложности, которые окружаю гадающего, просто так не решить. Необходима сильная поддержка сильных покровителей. Не стоит искать пути достижения своих целей, при этом посягая на чужое имущество. Карта выпадает людям с негативной энергетикой, способным совершать плохие поступки.',
        'хитрость, провал, переезд, предательство'),
       (55, '8 Мечей⚔️', 'Eight of Swords⚔️', 'classpath:images/taro-cards/Swords08.jpg',
        'Восьмерка мечей характеризует человека его непостоянством, какими-то сомнениями. Восьмерка мечей означает и внешние, и внутренние факторы, которые свидетельствуют о проблеме. Это могут быть объективные недоразумения и те, которые исходят от самого человека.',
        'слабость, препятствия, убеждения, ожидание неудачи'),
       (56, '9 Мечей⚔️', 'Nine of Swords⚔️', 'classpath:images/taro-cards/Swords09.jpg',
        'Девятка мечей характеризуется тревогами, напастями, паникой и чувством вины. Причиной всех проблем, которые происходят в жизни человека, является в первую очередь его бессилие перед неудачами. Но его также нельзя упрекать в том, что его страхи и переживания беспочвенны. Но среди более серьезных проблем также могут возникнуть и незначительные трудности, на которые действительно не стоит обращать внимания.',
        'страдания, сомнения, стыд, вина, событие'),
       (57, '10 Мечей⚔️', 'Ten of Swords⚔️', 'classpath:images/taro-cards/Swords10.jpg',
        'С десяткой мечей речь идет о бесконтрольном завершение каких-либо дел. Это могут быть неожиданные и деструктивные ситуации с крахом, распадом, развалом. И это не просто трудностив жизни человека, это полный тупик и безнадежность.',
        'отчаяние, утрата, стресс, бедствие, грабеж'),
       (58, 'Паж Мечей⚔️', 'Page of Swords⚔️', 'classpath:images/taro-cards/Swords11.jpg',
        'Паж мечей характеризуется недвусмысленным указанием на обновление, недюжинный уми провокацию. Карта имеет разное значение, но есть и то, что объединяет каждого. Когда выпадает этот Аркан, то можно судить о неординарной личности со значительным потенциалом. Речь может идти о человеке с непростым характером, и это, несомненно, выделяет его среди других людей. А еще карта может нести и более негативный характер, где человек и без того в непростой ситуации, пытается все усложнить, усугубить.',
        'шанс, возможность, начало конца, честность, решимость'),
       (59, 'Рыцарь Мечей⚔️', 'Knight of Swords⚔️', 'classpath:images/taro-cards/Swords12.jpg',
        'Рыцарь мечей гласит об ораторстве человека, его интеллектуальных способностях, независимом и хитром характере. Человека с этим Арканом можно назвать технократом и циником, которыйспособен быть коварным, в чем-то легкомысленным. Нельзя назвать эту карту простой, у нее достаточно много аспектов.',
        'упорство, порывистость, храбрость, гнев, защита'),
       (60, 'Королева Мечей⚔️', 'Queen of Swords⚔️', 'classpath:images/taro-cards/Swords13.jpg',
        'Королева мечей свидетельствует о независимой индивидуальности, у которой много мыслей и идей. Речь идет о сообразительном и прогрессивном человеке, обладающим аналитическим умом. За этой картой скрывается личность 30-45 лет, но бывают и исключения, это может быть человек значительно моложе этого возраста. А если карта выпала молодой персоне, это означает то, что она мудрая и умная не по годам.',
        'проницательность, сарказм, карьера, амбиции, печаль'),
       (61, 'Король Мечей⚔️', 'King of Swords⚔️', 'classpath:images/taro-cards/Swords14.jpg',
        'Король мечей говорит о высоком уровне интеллекта, объективности, разносторонности, гибком рациональном подходе к жизни. У человека под влиянием этого Аркана много интересов, он проницательная и разумная личность. Как правило, карта выпадает авторитетному человеку, к которому многие люди обращаются за советом. И человек, действительно, способен дать мудрый совет, рекомендацию, подсказку, так как обладает недюжинным умом советчика и огромным жизненным опытом.',
        'ответственность, контроль, агрессивность, властность'),

-- Карты Жезлов
       (50, 'Туз Жезлов⚕️', 'Ace of Wands⚕️', 'classpath:images/taro-cards/Wands01.jpg',
        'Туз жезлов обозначает перспективы, открывающиеся возможности для духовного развития, энергичность, смелость, масштабные проекты. Туз жезлов – это находчивость, мужество, готовность пойти на определённые жертвы. Кроме этого эта карта обозначает: оптимизм, вдохновение, раскрытие творческого потенциала человека.',
        'новые начинания, наследство, перемены, возможности, сила'),
       (62, '2 Жезлов⚕️', 'Two of Wands⚕️', 'classpath:images/taro-cards/Wands02.jpg',
        'Двойка жезлов свидетельствует о том, что человек готов бороться за себя, свои идеи и начинания, даже если ему придется рисковать. У человека проявляется выраженная импульсивность и безрассудная храбрость. Он больше не желает занимать нейтральную позицию, поэтому реагирует на все незамедлительно, не рассуждая и не раздумывая.',
        'интерес, нейтральная позиция, пустота, союз без тепла'),
       (63, '3 Жезлов⚕️', 'Three of Wands⚕️', 'classpath:images/taro-cards/Wands03.jpg',
        'Если в раскладе появилась Тройка жезлов, она обозначает стремление к идеалу, доброжелательное отношение к людям. Человек стремится реализовать свой творческий потенциал. Он ищет единомышленников для создания собственного дела или совместной работы над заданием.',
        'вершина, труд, материальное благополучие, поддержка'),
       (64, '4 Жезлов⚕️', 'Four of Wands⚕️', 'classpath:images/taro-cards/Wands04.jpg',
        'Карта Четвёрка жезлов - одна из самых удачных среди карт Таро. Она обозначает счастливый период, радость, удачу, наслаждение всеми благами жизни. Гадающий человек находится в гармонии со Вселенной. Он счастлив, общителен, оптимистично смотрит на события.',
        'гармония, мир, процветание, переезд'),
       (65, '5 Жезлов⚕️', 'Five of Wands⚕️', 'classpath:images/taro-cards/Wands05.jpg',
        'Аркан может обозначать перемены, которые не пойдут легким путем, и для них придется приложить усилия, преодолевая себя и жизненные трудности. Тут присутствует соперничество, но оно не деструктивное, а полезное, которое позволяет испытать свои силы, и укрепить уверенность в них в случае успеха.',
        'испытание, борьба, спор, задача, загадка'),
       (66, '6 Жезлов⚕️', 'Six of Wands⚕️', 'classpath:images/taro-cards/Wands06.jpg',
        'Шестерка жезлов – благоприятный аркан. В общем раскладе является символом победы, завершения всех поставленных задач, даже самых трудных. Человек, который делает расклад, может стать известным или получить народное признание за достойные заслуги, потому что основной смысл карты – обнародование победы.',
        'успех, завершение, лидерские качества, мудрые решения'),
       (67, '7 Жезлов⚕️', 'Seven of Wands⚕️', 'classpath:images/taro-cards/Wands07.jpg',
        'Семерка жезлов – неоднозначный аркан, связанный с риском, борьбой, бескомпромиссностью, некоторыми ограничениями. Правда все эти действия будут направлены на достижение цели. Человек будет оперативно прилагать свои усилия, ему свойственно добиваться успеха в одиночку, без чьей-либо помощи.',
        'мужество, критика, стойкость, напасть'),
       (68, '8 Жезлов⚕️', 'Eight of Wands⚕️', 'classpath:images/taro-cards/Wands08.jpg',
        'Восьмерка жезлов подразумевает вдохновение и озарение, когда человека будут ожидать события благоприятного характера. У него все получается, он быстро и правильно принимает решение. Под воздействием этой карты, он просто хватает свою удачу за хвост. Следует ожидать от жизни много интересных и незабываемых событий.',
        'зеленый свет, новые друзья, творческий прорыв, прогресс'),
       (69, '9 Жезлов⚕️', 'Nine of Wands⚕️', 'classpath:images/taro-cards/Wands09.jpg',
        'Основные трактовки девятки жезлов: чувство опасности, успех в нелёгкой борьбе, готовность к отстаиванию своих интересов, возможные страхи и опасения перед неизвестным будущим, ощущение приближающегося успеха. Гадающий совсем недавно пережил непростые времена. Ему пришлось добиваться своих целей нелёгким упорным трудом. Проблем и сложностей возникало предостаточно, но он выдержал их стойко.',
        'умпрямство, спокойствие, готовность к защите, финансовый успех'),
       (70, '10 Жезлов⚕️', 'Ten of Wands⚕️', 'classpath:images/taro-cards/Wands10.jpg',
        'Значение десятки жезлов - благоприятное. Оно обозначает удачу, процветание, финансовую выгоду. Но человек, на которого делают расклад, слишком уж усердно трудится, не замечая ничего вокруг себя. Вот почему все его достижения могут не принести ему морального удовлетворения, а в душе будет только пустота.',
        'решительность окупится, ненужный груз, измена, скрытность'),
       (71, 'Паж Жезлов⚕️', 'Page of Wands⚕️', 'classpath:images/taro-cards/Wands11.jpg',
        'Чаще всего Паж Жезлов трактуется как некий человек (молодой, светловолосый и светлоглазый), что вскоре появится в жизни гадающего. Это может быть и девушка, и юноша. Карта также может означать получение интересного и выгодного предложения, такого, что просто грех отказываться. Она означает и определенный настрой, состояние душа: подъем, энтузиазм, энергию (все, что свойственно здоровой молодости).',
        'молодой человек, танцор, энтузиазм, смелость'),
       (72, 'Рыцарь Жезлов⚕️', 'Knight of Wands⚕️', 'classpath:images/taro-cards/Wands12.jpg',
        'В том случае, если выпал Рыцарь Жезлов в прямом положении, карта символизирует человека – борца. Он всегда готов отстаивать свои интересы, не боясь сопутствующих проблем и козней конкурентов. В то же время, он – романтик, ему в жизни всё интересно. Любит споры и соревнования. Он долго не думает, а предпочитает активно и с энтузиазмом действовать для того, чтобы его цели были достигнуты. Не боится рисковать.',
        'риск, нетерпение, крайности, энтузиазм'),
       (73, 'Королева Жезлов⚕️', 'Queen of Wands⚕️', 'classpath:images/taro-cards/Wands13.jpg',
        'Королева жезлов представлена в виде опытной, зрелой и практичной женщины, которая состоялась в жизни. Нередко ее еще называют магнетической личностью, волевой и импозантной. Тому, кому выпадает этот Аркан, уверенный в себе, активный, инициативный, независимый, открытый и харизматичный человек. Он быстро и решительно принимает решение, но ему все равно требуется поддержка со стороны. Он отличается высоким профессионализмом, поэтому способен преодолевать препятствия, бросая вызов судьбе.',
        'успех, строгость, справедливость, любовь к роскоши'),
       (74, 'Король Жезлов⚕️', 'King of Wands⚕️', 'classpath:images/taro-cards/Wands14.jpg',
        'Когда выпал Король Жезлов, он говорит о том, что гадающий человек – лидер, способный повести за собой массы, обладающий даром убеждения. Эта карта символизирует покровителя. Такой человек проявляет завидное упорство в достижении своих целей.',
        'гордость, честолюбие, активность, быстрая реакция');
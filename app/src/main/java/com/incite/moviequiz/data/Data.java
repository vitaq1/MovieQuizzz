package com.incite.moviequiz.data;

import android.content.Context;

import com.incite.moviequiz.R;

import java.util.ArrayList;

public class Data {

    static ArrayList<FilmPack> packs = new ArrayList<>();
    static ArrayList<Film> films = null;
    public static boolean isLoaded = false;
    static FilmPack p1 = new FilmPack(1);
    static FilmPack p2 = new FilmPack(2);
    static FilmPack p3 = new FilmPack(3);
    static FilmPack p4 = new FilmPack(4);
    static FilmPack p5 = new FilmPack(5);
    static FilmPack p6 = new FilmPack(6);
    static FilmPack p7 = new FilmPack(7);
    static FilmPack p8 = new FilmPack(8);
    static FilmPack p9 = new FilmPack(9);
    static FilmPack p10 = new FilmPack(10);
    static FilmPack p11 = new FilmPack(11);
    static FilmPack p12 = new FilmPack(12);
    static FilmPack p13 = new FilmPack(13);
    static FilmPack p14 = new FilmPack(14);
    static FilmPack p15 = new FilmPack(15);
    static FilmPack p16 = new FilmPack(16);
    static FilmPack p17 = new FilmPack(17);
    static FilmPack p18 = new FilmPack(18);
    static FilmPack p19 = new FilmPack(19);
    static FilmPack p20 = new FilmPack(20);
    static FilmPack p21 = new FilmPack(21);
    static FilmPack p22 = new FilmPack(22);
    static FilmPack p23 = new FilmPack(23);
    static FilmPack p24 = new FilmPack(24);
    static FilmPack p25 = new FilmPack(25);
    static FilmPack p26 = new FilmPack(26);
    static FilmPack p27 = new FilmPack(27);
    static FilmPack p28 = new FilmPack(28);
    static FilmPack p29 = new FilmPack(29);
    static FilmPack p30 = new FilmPack(30);


    public static int overallFilms = 0;

    public static void loadPacks(Context context) {
        loadFilms(context);
        System.out.println("LOADED");
        /*p1.addFilms(films.get(0),
                films.get(1),
                films.get(2),
                films.get(3),
                films.get(4));
        packs.add(p1);*/

        p1.addFilms(
                films.get(1 - 1),
                films.get(40 - 1),
                films.get(5 - 1),
                films.get(13 - 1),
                films.get(18 - 1),
                films.get(36 - 1),
                films.get(58 - 1),
                films.get(83 - 1),
                films.get(91 - 1),
                films.get(112 - 1),
                films.get(126 - 1),
                films.get(160 - 1),
                films.get(230 - 1),
                films.get(273 - 1),
                films.get(286 - 1),
                films.get(302 - 1),
                films.get(304 - 1),
                films.get(351 - 1),
                films.get(371 - 1),
                films.get(434 - 1)
        );
        packs.add(p1);

        p2.addFilms(
                films.get(8 - 1),
                films.get(9 - 1),
                films.get(533 - 1),
                films.get(495 - 1),
                films.get(486 - 1),
                films.get(478 - 1),
                films.get(476 - 1),
                films.get(456 - 1),
                films.get(445 - 1),
                films.get(444 - 1),
                films.get(431 - 1),
                films.get(426 - 1),
                films.get(423 - 1),
                films.get(386 - 1),
                films.get(341 - 1),
                films.get(338 - 1),
                films.get(327 - 1),
                films.get(315 - 1),
                films.get(311 - 1),
                films.get(290 - 1)
        );
        packs.add(p2);

        p3.addFilms(
                films.get(2 - 1),
                films.get(28 - 1),
                films.get(33 - 1),
                films.get(35 - 1),
                films.get(55 - 1),
                films.get(59 - 1),
                films.get(81 - 1),
                films.get(97 - 1),
                films.get(141 - 1),
                films.get(150 - 1),
                films.get(151 - 1),
                films.get(152 - 1),
                films.get(153 - 1),
                films.get(188 - 1),
                films.get(190 - 1),
                films.get(196 - 1),
                films.get(210 - 1),
                films.get(213 - 1),
                films.get(240 - 1),
                films.get(242 - 1)
        );
        packs.add(p3);

        p4.addFilms(
                films.get(6 - 1),
                films.get(7 - 1),
                films.get(26 - 1),
                films.get(30 - 1),
                films.get(31 - 1),
                films.get(44 - 1),
                films.get(64 - 1),
                films.get(68 - 1),
                films.get(70 - 1),
                films.get(88 - 1),
                films.get(92 - 1),
                films.get(93 - 1),
                films.get(94 - 1),
                films.get(95 - 1),
                films.get(96 - 1),
                films.get(98 - 1),
                films.get(99 - 1),
                films.get(100 - 1),
                films.get(101 - 1),
                films.get(12 - 1)
        );
        packs.add(p4);

        p5.addFilms(
                films.get(3 - 1),
                films.get(10 - 1),
                films.get(14 - 1),
                films.get(191 - 1),
                films.get(192 - 1),
                films.get(193 - 1),
                films.get(194 - 1),
                films.get(195 - 1),
                films.get(391 - 1),
                films.get(392 - 1),
                films.get(393 - 1),
                films.get(394 - 1),
                films.get(413 - 1),
                films.get(414 - 1),
                films.get(415 - 1),
                films.get(416 - 1),
                films.get(417 - 1),
                films.get(418 - 1),
                films.get(419 - 1),
                films.get(420 - 1)
        );
        packs.add(p5);

        p6.addFilms(
                films.get(4 - 1),
                films.get(11 - 1),
                films.get(15 - 1),
                films.get(158 - 1),
                films.get(159 - 1),
                films.get(182 - 1),
                films.get(184 - 1),
                films.get(185 - 1),
                films.get(189 - 1),
                films.get(197 - 1),
                films.get(202 - 1),
                films.get(203 - 1),
                films.get(211 - 1),
                films.get(225 - 1),
                films.get(228 - 1),
                films.get(229 - 1),
                films.get(244 - 1),
                films.get(253 - 1),
                films.get(255 - 1),
                films.get(266 - 1)
        );
        packs.add(p6);

        p7.addFilms(
                films.get(20 - 1),
                films.get(21 - 1),
                films.get(22 - 1),
                films.get(23 - 1),
                films.get(24 - 1),
                films.get(25 - 1),
                films.get(51 - 1),
                films.get(52 - 1),
                films.get(53 - 1),
                films.get(54 - 1),
                films.get(128 - 1),
                films.get(129 - 1),
                films.get(130 - 1),
                films.get(131 - 1),
                films.get(132 - 1),
                films.get(133 - 1),
                films.get(134 - 1),
                films.get(135 - 1),
                films.get(139 - 1),
                films.get(140 - 1)
        );
        packs.add(p7);

        p8.addFilms(
                films.get(16 - 1),
                films.get(17 - 1),
                films.get(19 - 1),
                films.get(27 - 1),
                films.get(29 - 1),
                films.get(79 - 1),
                films.get(80 - 1),
                films.get(110 - 1),
                films.get(111 - 1),
                films.get(121 - 1),
                films.get(122 - 1),
                films.get(123 - 1),
                films.get(144 - 1),
                films.get(145 - 1),
                films.get(146 - 1),
                films.get(161 - 1),
                films.get(214 - 1),
                films.get(215 - 1),
                films.get(216 - 1),
                films.get(217 - 1)
        );
        packs.add(p8);

        p9.addFilms(
                films.get(32 - 1),
                films.get(37 - 1),
                films.get(38 - 1),
                films.get(39 - 1),
                films.get(41 - 1),
                films.get(42 - 1),
                films.get(61 - 1),
                films.get(62 - 1),
                films.get(63 - 1),
                films.get(65 - 1),
                films.get(66 - 1),
                films.get(84 - 1),
                films.get(85 - 1),
                films.get(86 - 1),
                films.get(87 - 1),
                films.get(89 - 1),
                films.get(105 - 1),
                films.get(106 - 1),
                films.get(107 - 1),
                films.get(108 - 1)
        );
        packs.add(p9);

        p10.addFilms(
                films.get(34 - 1),
                films.get(43 - 1),
                films.get(45 - 1),
                films.get(46 - 1),
                films.get(47 - 1),
                films.get(48 - 1),
                films.get(67 - 1),
                films.get(69 - 1),
                films.get(71 - 1),
                films.get(72 - 1),
                films.get(102 - 1),
                films.get(103 - 1),
                films.get(124 - 1),
                films.get(125 - 1),
                films.get(148 - 1),
                films.get(149 - 1),
                films.get(154 - 1),
                films.get(155 - 1),
                films.get(156 - 1),
                films.get(157 - 1)
        );
        packs.add(p10);

        p11.addFilms(
                films.get(435 - 1),
                films.get(436 - 1),
                films.get(437 - 1),
                films.get(438 - 1),
                films.get(446 - 1),
                films.get(447 - 1),
                films.get(448 - 1),
                films.get(449 - 1),
                films.get(468 - 1),
                films.get(469 - 1),
                films.get(470 - 1),
                films.get(491 - 1),
                films.get(492 - 1),
                films.get(493 - 1),
                films.get(494 - 1),
                films.get(509 - 1),
                films.get(510 - 1),
                films.get(511 - 1),
                films.get(512 - 1),
                films.get(513 - 1)
        );
        packs.add(p11);

        p12.addFilms(
                films.get(49 - 1),
                films.get(50 - 1),
                films.get(56 - 1),
                films.get(57 - 1),
                films.get(60 - 1),
                films.get(73 - 1),
                films.get(74 - 1),
                films.get(75 - 1),
                films.get(514 - 1),
                films.get(515 - 1),
                films.get(516 - 1),
                films.get(517 - 1),
                films.get(518 - 1),
                films.get(519 - 1),
                films.get(520 - 1),
                films.get(521 - 1),
                films.get(522 - 1),
                films.get(523 - 1),
                films.get(524 - 1),
                films.get(525 - 1)
        );
        packs.add(p12);

        p13.addFilms(
                films.get(573 - 1),
                films.get(574 - 1),
                films.get(583 - 1),
                films.get(584 - 1),
                films.get(585 - 1),
                films.get(586 - 1),
                films.get(587 - 1),
                films.get(588 - 1),
                films.get(589 - 1),
                films.get(590 - 1),
                films.get(591 - 1),
                films.get(592 - 1),
                films.get(593 - 1),
                films.get(594 - 1),
                films.get(595 - 1),
                films.get(596 - 1),
                films.get(597 - 1),
                films.get(598 - 1),
                films.get(599 - 1),
                films.get(600 - 1)
        );
        packs.add(p13);

        p14.addFilms(
                films.get(76 - 1),
                films.get(77 - 1),
                films.get(78 - 1),
                films.get(82 - 1),
                films.get(90 - 1),
                films.get(104 - 1),
                films.get(109 - 1),
                films.get(113 - 1),
                films.get(114 - 1),
                films.get(115 - 1),
                films.get(116 - 1),
                films.get(117 - 1),
                films.get(118 - 1),
                films.get(119 - 1),
                films.get(120 - 1),
                films.get(127 - 1),
                films.get(136 - 1),
                films.get(137 - 1),
                films.get(138 - 1),
                films.get(142 - 1)
        );
        packs.add(p14);

        p15.addFilms(
                films.get(544 - 1),
                films.get(545 - 1),
                films.get(546 - 1),
                films.get(547 - 1),
                films.get(548 - 1),
                films.get(549 - 1),
                films.get(550 - 1),
                films.get(568 - 1),
                films.get(569 - 1),
                films.get(570 - 1),
                films.get(571 - 1),
                films.get(572 - 1),
                films.get(575 - 1),
                films.get(576 - 1),
                films.get(577 - 1),
                films.get(578 - 1),
                films.get(579 - 1),
                films.get(580 - 1),
                films.get(581 - 1),
                films.get(582 - 1)
        );
        packs.add(p15);

        p16.addFilms(
                films.get(143 - 1),
                films.get(147 - 1),
                films.get(162 - 1),
                films.get(163 - 1),
                films.get(164 - 1),
                films.get(165 - 1),
                films.get(166 - 1),
                films.get(167 - 1),
                films.get(168 - 1),
                films.get(169 - 1),
                films.get(170 - 1),
                films.get(171 - 1),
                films.get(172 - 1),
                films.get(173 - 1),
                films.get(174 - 1),
                films.get(175 - 1),
                films.get(176 - 1),
                films.get(177 - 1),
                films.get(178 - 1),
                films.get(179 - 1)
        );
        packs.add(p16);

        p17.addFilms(
                films.get(180 - 1),
                films.get(181 - 1),
                films.get(183 - 1),
                films.get(186 - 1),
                films.get(187 - 1),
                films.get(198 - 1),
                films.get(199 - 1),
                films.get(200 - 1),
                films.get(201 - 1),
                films.get(204 - 1),
                films.get(205 - 1),
                films.get(206 - 1),
                films.get(207 - 1),
                films.get(208 - 1),
                films.get(209 - 1),
                films.get(212 - 1),
                films.get(219 - 1),
                films.get(220 - 1),
                films.get(221 - 1),
                films.get(222 - 1)
        );
        packs.add(p17);

        p18.addFilms(
                films.get(541 - 1),
                films.get(542 - 1),
                films.get(543 - 1),
                films.get(551 - 1),
                films.get(552 - 1),
                films.get(553 - 1),
                films.get(554 - 1),
                films.get(555 - 1),
                films.get(556 - 1),
                films.get(557 - 1),
                films.get(558 - 1),
                films.get(559 - 1),
                films.get(560 - 1),
                films.get(561 - 1),
                films.get(562 - 1),
                films.get(563 - 1),
                films.get(564 - 1),
                films.get(565 - 1),
                films.get(566 - 1),
                films.get(567 - 1)
        );
        packs.add(p18);

        p19.addFilms(
                films.get(352 - 1),
                films.get(353 - 1),
                films.get(354 - 1),
                films.get(355 - 1),
                films.get(356 - 1),
                films.get(357 - 1),
                films.get(358 - 1),
                films.get(359 - 1),
                films.get(360 - 1),
                films.get(361 - 1),
                films.get(362 - 1),
                films.get(363 - 1),
                films.get(364 - 1),
                films.get(365 - 1),
                films.get(366 - 1),
                films.get(367 - 1),
                films.get(368 - 1),
                films.get(369 - 1),
                films.get(370 - 1),
                films.get(372 - 1)
        );
        packs.add(p19);

        p20.addFilms(
                films.get(395 - 1),
                films.get(396 - 1),
                films.get(397 - 1),
                films.get(398 - 1),
                films.get(399 - 1),
                films.get(400 - 1),
                films.get(401 - 1),
                films.get(402 - 1),
                films.get(403 - 1),
                films.get(404 - 1),
                films.get(405 - 1),
                films.get(406 - 1),
                films.get(407 - 1),
                films.get(408 - 1),
                films.get(409 - 1),
                films.get(410 - 1),
                films.get(411 - 1),
                films.get(412 - 1),
                films.get(421 - 1),
                films.get(422 - 1)
        );
        packs.add(p20);

        p21.addFilms(
                films.get(503 - 1),
                films.get(504 - 1),
                films.get(505 - 1),
                films.get(506 - 1),
                films.get(507 - 1),
                films.get(508 - 1),
                films.get(526 - 1),
                films.get(527 - 1),
                films.get(528 - 1),
                films.get(529 - 1),
                films.get(530 - 1),
                films.get(531 - 1),
                films.get(532 - 1),
                films.get(534 - 1),
                films.get(535 - 1),
                films.get(536 - 1),
                films.get(537 - 1),
                films.get(538 - 1),
                films.get(539 - 1),
                films.get(540 - 1)
        );
        packs.add(p21);

        p22.addFilms(
                films.get(256 - 1),
                films.get(257 - 1),
                films.get(258 - 1),
                films.get(259 - 1),
                films.get(260 - 1),
                films.get(261 - 1),
                films.get(262 - 1),
                films.get(263 - 1),
                films.get(264 - 1),
                films.get(265 - 1),
                films.get(267 - 1),
                films.get(268 - 1),
                films.get(269 - 1),
                films.get(270 - 1),
                films.get(271 - 1),
                films.get(272 - 1),
                films.get(274 - 1),
                films.get(275 - 1),
                films.get(276 - 1),
                films.get(277 - 1)
        );
        packs.add(p22);

        p23.addFilms(
                films.get(450 - 1),
                films.get(451 - 1),
                films.get(452 - 1),
                films.get(453 - 1),
                films.get(454 - 1),
                films.get(455 - 1),
                films.get(457 - 1),
                films.get(458 - 1),
                films.get(459 - 1),
                films.get(460 - 1),
                films.get(461 - 1),
                films.get(462 - 1),
                films.get(463 - 1),
                films.get(464 - 1),
                films.get(465 - 1),
                films.get(466 - 1),
                films.get(467 - 1),
                films.get(471 - 1),
                films.get(477 - 1),
                films.get(479 - 1)
        );
        packs.add(p23);

        p24.addFilms(
                films.get(373 - 1),
                films.get(374 - 1),
                films.get(375 - 1),
                films.get(376 - 1),
                films.get(377 - 1),
                films.get(378 - 1),
                films.get(379 - 1),
                films.get(380 - 1),
                films.get(381 - 1),
                films.get(382 - 1),
                films.get(383 - 1),
                films.get(384 - 1),
                films.get(385 - 1),
                films.get(387 - 1),
                films.get(388 - 1),
                films.get(389 - 1),
                films.get(390 - 1),
                films.get(424 - 1),
                films.get(425 - 1),
                films.get(427 - 1)
        );
        packs.add(p24);

        p25.addFilms(
                films.get(218 - 1),
                films.get(223 - 1),
                films.get(224 - 1),
                films.get(226 - 1),
                films.get(227 - 1),
                films.get(231 - 1),
                films.get(232 - 1),
                films.get(233 - 1),
                films.get(234 - 1),
                films.get(235 - 1),
                films.get(236 - 1),
                films.get(237 - 1),
                films.get(238 - 1),
                films.get(239 - 1),
                films.get(241 - 1),
                films.get(243 - 1),
                films.get(245 - 1),
                films.get(246 - 1),
                films.get(247 - 1),
                films.get(248 - 1)
        );
        packs.add(p25);

        p26.addFilms(
                films.get(249 - 1),
                films.get(250 - 1),
                films.get(251 - 1),
                films.get(252 - 1),
                films.get(254 - 1),
                films.get(342 - 1),
                films.get(343 - 1),
                films.get(344 - 1),
                films.get(345 - 1),
                films.get(346 - 1),
                films.get(347 - 1),
                films.get(348 - 1),
                films.get(349 - 1),
                films.get(350 - 1),
                films.get(428 - 1),
                films.get(429 - 1),
                films.get(430 - 1),
                films.get(432 - 1),
                films.get(433 - 1),
                films.get(439 - 1)
        );
        packs.add(p26);

        p27.addFilms(
                films.get(440 - 1),
                films.get(441 - 1),
                films.get(442 - 1),
                films.get(443 - 1),
                films.get(472 - 1),
                films.get(278 - 1),
                films.get(279 - 1),
                films.get(280 - 1),
                films.get(281 - 1),
                films.get(282 - 1),
                films.get(283 - 1),
                films.get(284 - 1),
                films.get(285 - 1),
                films.get(287 - 1),
                films.get(288 - 1),
                films.get(289 - 1),
                films.get(291 - 1),
                films.get(292 - 1),
                films.get(293 - 1),
                films.get(294 - 1)
        );
        packs.add(p27);

        p28.addFilms(
                films.get(473 - 1),
                films.get(474 - 1),
                films.get(475 - 1),
                films.get(480 - 1),
                films.get(481 - 1),
                films.get(482 - 1),
                films.get(483 - 1),
                films.get(484 - 1),
                films.get(485 - 1),
                films.get(487 - 1),
                films.get(488 - 1),
                films.get(489 - 1),
                films.get(490 - 1),
                films.get(496 - 1),
                films.get(497 - 1),
                films.get(498 - 1),
                films.get(499 - 1),
                films.get(500 - 1),
                films.get(501 - 1),
                films.get(502 - 1)
        );
        packs.add(p28);

        p29.addFilms(
                films.get(295 - 1),
                films.get(296 - 1),
                films.get(297 - 1),
                films.get(298 - 1),
                films.get(299 - 1),
                films.get(300 - 1),
                films.get(301 - 1),
                films.get(303 - 1),
                films.get(305 - 1),
                films.get(306 - 1),
                films.get(307 - 1),
                films.get(308 - 1),
                films.get(309 - 1),
                films.get(310 - 1),
                films.get(312 - 1),
                films.get(313 - 1),
                films.get(314 - 1),
                films.get(316 - 1),
                films.get(317 - 1),
                films.get(318 - 1)
        );
        packs.add(p29);

        p30.addFilms(
                films.get(319 - 1),
                films.get(320 - 1),
                films.get(321 - 1),
                films.get(322 - 1),
                films.get(323 - 1),
                films.get(324 - 1),
                films.get(325 - 1),
                films.get(326 - 1),
                films.get(328 - 1),
                films.get(329 - 1),
                films.get(330 - 1),
                films.get(331 - 1),
                films.get(332 - 1),
                films.get(333 - 1),
                films.get(334 - 1),
                films.get(335 - 1),
                films.get(336 - 1),
                films.get(337 - 1),
                films.get(339 - 1),
                films.get(340 - 1)
        );
        packs.add(p30);


        //////////////////////////////////////


        /** OVERALL FILMS **/
        for (int i = 0; i < getPacks().size(); i++) {
            for (int j = 0; j < getPacks().get(i).size; j++) {
                overallFilms++;
            }
        }

    }

    public static ArrayList<FilmPack> getPacks() {
        return packs;
    }

    private static void loadFilms(Context context) {
        films = new ArrayList<>();
        System.out.println("123");
        films.add(new Film(context.getString(R.string.s1), R.drawable.f1));
        films.add(new Film(context.getString(R.string.s2), R.drawable.f2));
        films.add(new Film(context.getString(R.string.s3), R.drawable.f3));
        films.add(new Film(context.getString(R.string.s4), R.drawable.f4));
        films.add(new Film(context.getString(R.string.s5), R.drawable.f5));
        films.add(new Film(context.getString(R.string.s6), R.drawable.f6));
        films.add(new Film(context.getString(R.string.s7), R.drawable.f7));
        films.add(new Film(context.getString(R.string.s8), R.drawable.f8));
        films.add(new Film(context.getString(R.string.s9), R.drawable.f9));
        films.add(new Film(context.getString(R.string.s10), R.drawable.f10));
        films.add(new Film(context.getString(R.string.s11), R.drawable.f11));
        films.add(new Film(context.getString(R.string.s12), R.drawable.f12));
        films.add(new Film(context.getString(R.string.s13), R.drawable.f13));
        films.add(new Film(context.getString(R.string.s14), R.drawable.f14));
        films.add(new Film(context.getString(R.string.s15), R.drawable.f15));
        films.add(new Film(context.getString(R.string.s16), R.drawable.f16));
        films.add(new Film(context.getString(R.string.s17), R.drawable.f17));
        films.add(new Film(context.getString(R.string.s18), R.drawable.f18));
        films.add(new Film(context.getString(R.string.s19), R.drawable.f19));
        films.add(new Film(context.getString(R.string.s20), R.drawable.f20));
        films.add(new Film(context.getString(R.string.s21), R.drawable.f21));
        films.add(new Film(context.getString(R.string.s22), R.drawable.f22));
        films.add(new Film(context.getString(R.string.s23), R.drawable.f23));
        films.add(new Film(context.getString(R.string.s24), R.drawable.f24));
        films.add(new Film(context.getString(R.string.s25), R.drawable.f25));
        films.add(new Film(context.getString(R.string.s26), R.drawable.f26));
        films.add(new Film(context.getString(R.string.s27), R.drawable.f27));
        films.add(new Film(context.getString(R.string.s28), R.drawable.f28));
        films.add(new Film(context.getString(R.string.s29), R.drawable.f29));
        films.add(new Film(context.getString(R.string.s30), R.drawable.f30));
        films.add(new Film(context.getString(R.string.s31), R.drawable.f31));
        films.add(new Film(context.getString(R.string.s32), R.drawable.f32));
        films.add(new Film(context.getString(R.string.s33), R.drawable.f33));
        films.add(new Film(context.getString(R.string.s34), R.drawable.f34));
        films.add(new Film(context.getString(R.string.s35), R.drawable.f35));
        films.add(new Film(context.getString(R.string.s36), R.drawable.f36));
        films.add(new Film(context.getString(R.string.s37), R.drawable.f37));
        films.add(new Film(context.getString(R.string.s38), R.drawable.f38));
        films.add(new Film(context.getString(R.string.s39), R.drawable.f39));
        films.add(new Film(context.getString(R.string.s40), R.drawable.f40));
        films.add(new Film(context.getString(R.string.s41), R.drawable.f41));
        films.add(new Film(context.getString(R.string.s42), R.drawable.f42));
        films.add(new Film(context.getString(R.string.s43), R.drawable.f43));
        films.add(new Film(context.getString(R.string.s44), R.drawable.f44));
        films.add(new Film(context.getString(R.string.s45), R.drawable.f45));
        films.add(new Film(context.getString(R.string.s46), R.drawable.f46));
        films.add(new Film(context.getString(R.string.s47), R.drawable.f47));
        films.add(new Film(context.getString(R.string.s48), R.drawable.f48));
        films.add(new Film(context.getString(R.string.s49), R.drawable.f49));
        films.add(new Film(context.getString(R.string.s50), R.drawable.f50));
        films.add(new Film(context.getString(R.string.s51), R.drawable.f51));
        films.add(new Film(context.getString(R.string.s52), R.drawable.f52));
        films.add(new Film(context.getString(R.string.s53), R.drawable.f53));
        films.add(new Film(context.getString(R.string.s54), R.drawable.f54));
        films.add(new Film(context.getString(R.string.s55), R.drawable.f55));
        films.add(new Film(context.getString(R.string.s56), R.drawable.f56));
        films.add(new Film(context.getString(R.string.s57), R.drawable.f57));
        films.add(new Film(context.getString(R.string.s58), R.drawable.f58));
        films.add(new Film(context.getString(R.string.s59), R.drawable.f59));
        films.add(new Film(context.getString(R.string.s60), R.drawable.f60));
        films.add(new Film(context.getString(R.string.s61), R.drawable.f61));
        films.add(new Film(context.getString(R.string.s62), R.drawable.f62));
        films.add(new Film(context.getString(R.string.s63), R.drawable.f63));
        films.add(new Film(context.getString(R.string.s64), R.drawable.f64));
        films.add(new Film(context.getString(R.string.s65), R.drawable.f65));
        films.add(new Film(context.getString(R.string.s66), R.drawable.f66));
        films.add(new Film(context.getString(R.string.s67), R.drawable.f67));
        films.add(new Film(context.getString(R.string.s68), R.drawable.f68));
        films.add(new Film(context.getString(R.string.s69), R.drawable.f69));
        films.add(new Film(context.getString(R.string.s70), R.drawable.f70));
        films.add(new Film(context.getString(R.string.s71), R.drawable.f71));
        films.add(new Film(context.getString(R.string.s72), R.drawable.f72));
        films.add(new Film(context.getString(R.string.s73), R.drawable.f73));
        films.add(new Film(context.getString(R.string.s74), R.drawable.f74));
        films.add(new Film(context.getString(R.string.s75), R.drawable.f75));
        films.add(new Film(context.getString(R.string.s76), R.drawable.f76));
        films.add(new Film(context.getString(R.string.s77), R.drawable.f77));
        films.add(new Film(context.getString(R.string.s78), R.drawable.f78));
        films.add(new Film(context.getString(R.string.s79), R.drawable.f79));
        films.add(new Film(context.getString(R.string.s80), R.drawable.f80));
        films.add(new Film(context.getString(R.string.s81), R.drawable.f81));
        films.add(new Film(context.getString(R.string.s82), R.drawable.f82));
        films.add(new Film(context.getString(R.string.s83), R.drawable.f83));
        films.add(new Film(context.getString(R.string.s84), R.drawable.f84));
        films.add(new Film(context.getString(R.string.s85), R.drawable.f85));
        films.add(new Film(context.getString(R.string.s86), R.drawable.f86));
        films.add(new Film(context.getString(R.string.s87), R.drawable.f87));
        films.add(new Film(context.getString(R.string.s88), R.drawable.f88));
        films.add(new Film(context.getString(R.string.s89), R.drawable.f89));
        films.add(new Film(context.getString(R.string.s90), R.drawable.f90));
        films.add(new Film(context.getString(R.string.s91), R.drawable.f91));
        films.add(new Film(context.getString(R.string.s92), R.drawable.f92));
        films.add(new Film(context.getString(R.string.s93), R.drawable.f93));
        films.add(new Film(context.getString(R.string.s94), R.drawable.f94));
        films.add(new Film(context.getString(R.string.s95), R.drawable.f95));
        films.add(new Film(context.getString(R.string.s96), R.drawable.f96));
        films.add(new Film(context.getString(R.string.s97), R.drawable.f97));
        films.add(new Film(context.getString(R.string.s98), R.drawable.f98));
        films.add(new Film(context.getString(R.string.s99), R.drawable.f99));
        films.add(new Film(context.getString(R.string.s100), R.drawable.f100));
        films.add(new Film(context.getString(R.string.s101), R.drawable.f101));
        films.add(new Film(context.getString(R.string.s102), R.drawable.f102));
        films.add(new Film(context.getString(R.string.s103), R.drawable.f103));
        films.add(new Film(context.getString(R.string.s104), R.drawable.f104));
        films.add(new Film(context.getString(R.string.s105), R.drawable.f105));
        films.add(new Film(context.getString(R.string.s106), R.drawable.f106));
        films.add(new Film(context.getString(R.string.s107), R.drawable.f107));
        films.add(new Film(context.getString(R.string.s108), R.drawable.f108));
        films.add(new Film(context.getString(R.string.s109), R.drawable.f109));
        films.add(new Film(context.getString(R.string.s110), R.drawable.f110));
        films.add(new Film(context.getString(R.string.s111), R.drawable.f111));
        films.add(new Film(context.getString(R.string.s112), R.drawable.f112));
        films.add(new Film(context.getString(R.string.s113), R.drawable.f113));
        films.add(new Film(context.getString(R.string.s114), R.drawable.f114));
        films.add(new Film(context.getString(R.string.s115), R.drawable.f115));
        films.add(new Film(context.getString(R.string.s116), R.drawable.f116));
        films.add(new Film(context.getString(R.string.s117), R.drawable.f117));
        films.add(new Film(context.getString(R.string.s118), R.drawable.f118));
        films.add(new Film(context.getString(R.string.s119), R.drawable.f119));
        films.add(new Film(context.getString(R.string.s120), R.drawable.f120));
        films.add(new Film(context.getString(R.string.s121), R.drawable.f121));
        films.add(new Film(context.getString(R.string.s122), R.drawable.f122));
        films.add(new Film(context.getString(R.string.s123), R.drawable.f123));
        films.add(new Film(context.getString(R.string.s124), R.drawable.f124));
        films.add(new Film(context.getString(R.string.s125), R.drawable.f125));
        films.add(new Film(context.getString(R.string.s126), R.drawable.f126));
        films.add(new Film(context.getString(R.string.s127), R.drawable.f127));
        films.add(new Film(context.getString(R.string.s128), R.drawable.f128));
        films.add(new Film(context.getString(R.string.s129), R.drawable.f129));
        films.add(new Film(context.getString(R.string.s130), R.drawable.f130));
        films.add(new Film(context.getString(R.string.s131), R.drawable.f131));
        films.add(new Film(context.getString(R.string.s132), R.drawable.f132));
        films.add(new Film(context.getString(R.string.s133), R.drawable.f133));
        films.add(new Film(context.getString(R.string.s134), R.drawable.f134));
        films.add(new Film(context.getString(R.string.s135), R.drawable.f135));
        films.add(new Film(context.getString(R.string.s136), R.drawable.f136));
        films.add(new Film(context.getString(R.string.s137), R.drawable.f137));
        films.add(new Film(context.getString(R.string.s138), R.drawable.f138));
        films.add(new Film(context.getString(R.string.s139), R.drawable.f139));
        films.add(new Film(context.getString(R.string.s140), R.drawable.f140));
        films.add(new Film(context.getString(R.string.s141), R.drawable.f141));
        films.add(new Film(context.getString(R.string.s142), R.drawable.f142));
        films.add(new Film(context.getString(R.string.s143), R.drawable.f143));
        films.add(new Film(context.getString(R.string.s144), R.drawable.f144));
        films.add(new Film(context.getString(R.string.s145), R.drawable.f145));
        films.add(new Film(context.getString(R.string.s146), R.drawable.f146));
        films.add(new Film(context.getString(R.string.s147), R.drawable.f147));
        films.add(new Film(context.getString(R.string.s148), R.drawable.f148));
        films.add(new Film(context.getString(R.string.s149), R.drawable.f149));
        films.add(new Film(context.getString(R.string.s150), R.drawable.f150));
        films.add(new Film(context.getString(R.string.s151), R.drawable.f151));
        films.add(new Film(context.getString(R.string.s152), R.drawable.f152));
        films.add(new Film(context.getString(R.string.s153), R.drawable.f153));
        films.add(new Film(context.getString(R.string.s154), R.drawable.f154));
        films.add(new Film(context.getString(R.string.s155), R.drawable.f155));
        films.add(new Film(context.getString(R.string.s156), R.drawable.f156));
        films.add(new Film(context.getString(R.string.s157), R.drawable.f157));
        films.add(new Film(context.getString(R.string.s158), R.drawable.f158));
        films.add(new Film(context.getString(R.string.s159), R.drawable.f159));
        films.add(new Film(context.getString(R.string.s160), R.drawable.f160));
        films.add(new Film(context.getString(R.string.s161), R.drawable.f161));
        films.add(new Film(context.getString(R.string.s162), R.drawable.f162));
        films.add(new Film(context.getString(R.string.s163), R.drawable.f163));
        films.add(new Film(context.getString(R.string.s164), R.drawable.f164));
        films.add(new Film(context.getString(R.string.s165), R.drawable.f165));
        films.add(new Film(context.getString(R.string.s166), R.drawable.f166));
        films.add(new Film(context.getString(R.string.s167), R.drawable.f167));
        films.add(new Film(context.getString(R.string.s168), R.drawable.f168));
        films.add(new Film(context.getString(R.string.s169), R.drawable.f169));
        films.add(new Film(context.getString(R.string.s170), R.drawable.f170));
        films.add(new Film(context.getString(R.string.s171), R.drawable.f171));
        films.add(new Film(context.getString(R.string.s172), R.drawable.f172));
        films.add(new Film(context.getString(R.string.s173), R.drawable.f173));
        films.add(new Film(context.getString(R.string.s174), R.drawable.f174));
        films.add(new Film(context.getString(R.string.s175), R.drawable.f175));
        films.add(new Film(context.getString(R.string.s176), R.drawable.f176));
        films.add(new Film(context.getString(R.string.s177), R.drawable.f177));
        films.add(new Film(context.getString(R.string.s178), R.drawable.f178));
        films.add(new Film(context.getString(R.string.s179), R.drawable.f179));
        films.add(new Film(context.getString(R.string.s180), R.drawable.f180));
        films.add(new Film(context.getString(R.string.s181), R.drawable.f181));
        films.add(new Film(context.getString(R.string.s182), R.drawable.f182));
        films.add(new Film(context.getString(R.string.s183), R.drawable.f183));
        films.add(new Film(context.getString(R.string.s184), R.drawable.f184));
        films.add(new Film(context.getString(R.string.s185), R.drawable.f185));
        films.add(new Film(context.getString(R.string.s186), R.drawable.f186));
        films.add(new Film(context.getString(R.string.s187), R.drawable.f187));
        films.add(new Film(context.getString(R.string.s188), R.drawable.f188));
        films.add(new Film(context.getString(R.string.s189), R.drawable.f189));
        films.add(new Film(context.getString(R.string.s190), R.drawable.f190));
        films.add(new Film(context.getString(R.string.s191), R.drawable.f191));
        films.add(new Film(context.getString(R.string.s192), R.drawable.f192));
        films.add(new Film(context.getString(R.string.s193), R.drawable.f193));
        films.add(new Film(context.getString(R.string.s194), R.drawable.f194));
        films.add(new Film(context.getString(R.string.s195), R.drawable.f195));
        films.add(new Film(context.getString(R.string.s196), R.drawable.f196));
        films.add(new Film(context.getString(R.string.s197), R.drawable.f197));
        films.add(new Film(context.getString(R.string.s198), R.drawable.f198));
        films.add(new Film(context.getString(R.string.s199), R.drawable.f199));
        films.add(new Film(context.getString(R.string.s200), R.drawable.f200));
        films.add(new Film(context.getString(R.string.s201), R.drawable.f201));
        films.add(new Film(context.getString(R.string.s202), R.drawable.f202));
        films.add(new Film(context.getString(R.string.s203), R.drawable.f203));
        films.add(new Film(context.getString(R.string.s204), R.drawable.f204));
        films.add(new Film(context.getString(R.string.s205), R.drawable.f205));
        films.add(new Film(context.getString(R.string.s206), R.drawable.f206));
        films.add(new Film(context.getString(R.string.s207), R.drawable.f207));
        films.add(new Film(context.getString(R.string.s208), R.drawable.f208));
        films.add(new Film(context.getString(R.string.s209), R.drawable.f209));
        films.add(new Film(context.getString(R.string.s210), R.drawable.f210));
        films.add(new Film(context.getString(R.string.s211), R.drawable.f211));
        films.add(new Film(context.getString(R.string.s212), R.drawable.f212));
        films.add(new Film(context.getString(R.string.s213), R.drawable.f213));
        films.add(new Film(context.getString(R.string.s214), R.drawable.f214));
        films.add(new Film(context.getString(R.string.s215), R.drawable.f215));
        films.add(new Film(context.getString(R.string.s216), R.drawable.f216));
        films.add(new Film(context.getString(R.string.s217), R.drawable.f217));
        films.add(new Film(context.getString(R.string.s218), R.drawable.f218));
        films.add(new Film(context.getString(R.string.s219), R.drawable.f219));
        films.add(new Film(context.getString(R.string.s220), R.drawable.f220));
        films.add(new Film(context.getString(R.string.s221), R.drawable.f221));
        films.add(new Film(context.getString(R.string.s222), R.drawable.f222));
        films.add(new Film(context.getString(R.string.s223), R.drawable.f223));
        films.add(new Film(context.getString(R.string.s224), R.drawable.f224));
        films.add(new Film(context.getString(R.string.s225), R.drawable.f225));
        films.add(new Film(context.getString(R.string.s226), R.drawable.f226));
        films.add(new Film(context.getString(R.string.s227), R.drawable.f227));
        films.add(new Film(context.getString(R.string.s228), R.drawable.f228));
        films.add(new Film(context.getString(R.string.s229), R.drawable.f229));
        films.add(new Film(context.getString(R.string.s230), R.drawable.f230));
        films.add(new Film(context.getString(R.string.s231), R.drawable.f231));
        films.add(new Film(context.getString(R.string.s232), R.drawable.f232));
        films.add(new Film(context.getString(R.string.s233), R.drawable.f233));
        films.add(new Film(context.getString(R.string.s234), R.drawable.f234));
        films.add(new Film(context.getString(R.string.s235), R.drawable.f235));
        films.add(new Film(context.getString(R.string.s236), R.drawable.f236));
        films.add(new Film(context.getString(R.string.s237), R.drawable.f237));
        films.add(new Film(context.getString(R.string.s238), R.drawable.f238));
        films.add(new Film(context.getString(R.string.s239), R.drawable.f239));
        films.add(new Film(context.getString(R.string.s240), R.drawable.f240));
        films.add(new Film(context.getString(R.string.s241), R.drawable.f241));
        films.add(new Film(context.getString(R.string.s242), R.drawable.f242));
        films.add(new Film(context.getString(R.string.s243), R.drawable.f243));
        films.add(new Film(context.getString(R.string.s244), R.drawable.f244));
        films.add(new Film(context.getString(R.string.s245), R.drawable.f245));
        films.add(new Film(context.getString(R.string.s246), R.drawable.f246));
        films.add(new Film(context.getString(R.string.s247), R.drawable.f247));
        films.add(new Film(context.getString(R.string.s248), R.drawable.f248));
        films.add(new Film(context.getString(R.string.s249), R.drawable.f249));
        films.add(new Film(context.getString(R.string.s250), R.drawable.f250));
        films.add(new Film(context.getString(R.string.s251), R.drawable.f251));
        films.add(new Film(context.getString(R.string.s252), R.drawable.f252));
        films.add(new Film(context.getString(R.string.s253), R.drawable.f253));
        films.add(new Film(context.getString(R.string.s254), R.drawable.f254));
        films.add(new Film(context.getString(R.string.s255), R.drawable.f255));
        films.add(new Film(context.getString(R.string.s256), R.drawable.f256));
        films.add(new Film(context.getString(R.string.s257), R.drawable.f257));
        films.add(new Film(context.getString(R.string.s258), R.drawable.f258));
        films.add(new Film(context.getString(R.string.s259), R.drawable.f259));
        films.add(new Film(context.getString(R.string.s260), R.drawable.f260));
        films.add(new Film(context.getString(R.string.s261), R.drawable.f261));
        films.add(new Film(context.getString(R.string.s262), R.drawable.f262));
        films.add(new Film(context.getString(R.string.s263), R.drawable.f263));
        films.add(new Film(context.getString(R.string.s264), R.drawable.f264));
        films.add(new Film(context.getString(R.string.s265), R.drawable.f265));
        films.add(new Film(context.getString(R.string.s266), R.drawable.f266));
        films.add(new Film(context.getString(R.string.s267), R.drawable.f267));
        films.add(new Film(context.getString(R.string.s268), R.drawable.f268));
        films.add(new Film(context.getString(R.string.s269), R.drawable.f269));
        films.add(new Film(context.getString(R.string.s270), R.drawable.f270));
        films.add(new Film(context.getString(R.string.s271), R.drawable.f271));
        films.add(new Film(context.getString(R.string.s272), R.drawable.f272));
        films.add(new Film(context.getString(R.string.s273), R.drawable.f273));
        films.add(new Film(context.getString(R.string.s274), R.drawable.f274));
        films.add(new Film(context.getString(R.string.s275), R.drawable.f275));
        films.add(new Film(context.getString(R.string.s276), R.drawable.f276));
        films.add(new Film(context.getString(R.string.s277), R.drawable.f277));
        films.add(new Film(context.getString(R.string.s278), R.drawable.f278));
        films.add(new Film(context.getString(R.string.s279), R.drawable.f279));
        films.add(new Film(context.getString(R.string.s280), R.drawable.f280));
        films.add(new Film(context.getString(R.string.s281), R.drawable.f281));
        films.add(new Film(context.getString(R.string.s282), R.drawable.f282));
        films.add(new Film(context.getString(R.string.s283), R.drawable.f283));
        films.add(new Film(context.getString(R.string.s284), R.drawable.f284));
        films.add(new Film(context.getString(R.string.s285), R.drawable.f285));
        films.add(new Film(context.getString(R.string.s286), R.drawable.f286));
        films.add(new Film(context.getString(R.string.s287), R.drawable.f287));
        films.add(new Film(context.getString(R.string.s288), R.drawable.f288));
        films.add(new Film(context.getString(R.string.s289), R.drawable.f289));
        films.add(new Film(context.getString(R.string.s290), R.drawable.f290));
        films.add(new Film(context.getString(R.string.s291), R.drawable.f291));
        films.add(new Film(context.getString(R.string.s292), R.drawable.f292));
        films.add(new Film(context.getString(R.string.s293), R.drawable.f293));
        films.add(new Film(context.getString(R.string.s294), R.drawable.f294));
        films.add(new Film(context.getString(R.string.s295), R.drawable.f295));
        films.add(new Film(context.getString(R.string.s296), R.drawable.f296));
        films.add(new Film(context.getString(R.string.s297), R.drawable.f297));
        films.add(new Film(context.getString(R.string.s298), R.drawable.f298));
        films.add(new Film(context.getString(R.string.s299), R.drawable.f299));
        films.add(new Film(context.getString(R.string.s300), R.drawable.f300));
        films.add(new Film(context.getString(R.string.s301), R.drawable.f301));
        films.add(new Film(context.getString(R.string.s302), R.drawable.f302));
        films.add(new Film(context.getString(R.string.s303), R.drawable.f303));
        films.add(new Film(context.getString(R.string.s304), R.drawable.f304));
        films.add(new Film(context.getString(R.string.s305), R.drawable.f305));
        films.add(new Film(context.getString(R.string.s306), R.drawable.f306));
        films.add(new Film(context.getString(R.string.s307), R.drawable.f307));
        films.add(new Film(context.getString(R.string.s308), R.drawable.f308));
        films.add(new Film(context.getString(R.string.s309), R.drawable.f309));
        films.add(new Film(context.getString(R.string.s310), R.drawable.f310));
        films.add(new Film(context.getString(R.string.s311), R.drawable.f311));
        films.add(new Film(context.getString(R.string.s312), R.drawable.f312));
        films.add(new Film(context.getString(R.string.s313), R.drawable.f313));
        films.add(new Film(context.getString(R.string.s314), R.drawable.f314));
        films.add(new Film(context.getString(R.string.s315), R.drawable.f315));
        films.add(new Film(context.getString(R.string.s316), R.drawable.f316));
        films.add(new Film(context.getString(R.string.s317), R.drawable.f317));
        films.add(new Film(context.getString(R.string.s318), R.drawable.f318));
        films.add(new Film(context.getString(R.string.s319), R.drawable.f319));
        films.add(new Film(context.getString(R.string.s320), R.drawable.f320));
        films.add(new Film(context.getString(R.string.s321), R.drawable.f321));
        films.add(new Film(context.getString(R.string.s322), R.drawable.f322));
        films.add(new Film(context.getString(R.string.s323), R.drawable.f323));
        films.add(new Film(context.getString(R.string.s324), R.drawable.f324));
        films.add(new Film(context.getString(R.string.s325), R.drawable.f325));
        films.add(new Film(context.getString(R.string.s326), R.drawable.f326));
        films.add(new Film(context.getString(R.string.s327), R.drawable.f327));
        films.add(new Film(context.getString(R.string.s328), R.drawable.f328));
        films.add(new Film(context.getString(R.string.s329), R.drawable.f329));
        films.add(new Film(context.getString(R.string.s330), R.drawable.f330));
        films.add(new Film(context.getString(R.string.s331), R.drawable.f331));
        films.add(new Film(context.getString(R.string.s332), R.drawable.f332));
        films.add(new Film(context.getString(R.string.s333), R.drawable.f333));
        films.add(new Film(context.getString(R.string.s334), R.drawable.f334));
        films.add(new Film(context.getString(R.string.s335), R.drawable.f335));
        films.add(new Film(context.getString(R.string.s336), R.drawable.f336));
        films.add(new Film(context.getString(R.string.s337), R.drawable.f337));
        films.add(new Film(context.getString(R.string.s338), R.drawable.f338));
        films.add(new Film(context.getString(R.string.s339), R.drawable.f339));
        films.add(new Film(context.getString(R.string.s340), R.drawable.f340));
        films.add(new Film(context.getString(R.string.s341), R.drawable.f341));
        films.add(new Film(context.getString(R.string.s342), R.drawable.f342));
        films.add(new Film(context.getString(R.string.s343), R.drawable.f343));
        films.add(new Film(context.getString(R.string.s344), R.drawable.f344));
        films.add(new Film(context.getString(R.string.s345), R.drawable.f345));
        films.add(new Film(context.getString(R.string.s346), R.drawable.f346));
        films.add(new Film(context.getString(R.string.s347), R.drawable.f347));
        films.add(new Film(context.getString(R.string.s348), R.drawable.f348));
        films.add(new Film(context.getString(R.string.s349), R.drawable.f349));
        films.add(new Film(context.getString(R.string.s350), R.drawable.f350));
        films.add(new Film(context.getString(R.string.s351), R.drawable.f351));
        films.add(new Film(context.getString(R.string.s352), R.drawable.f352));
        films.add(new Film(context.getString(R.string.s353), R.drawable.f353));
        films.add(new Film(context.getString(R.string.s354), R.drawable.f354));
        films.add(new Film(context.getString(R.string.s355), R.drawable.f355));
        films.add(new Film(context.getString(R.string.s356), R.drawable.f356));
        films.add(new Film(context.getString(R.string.s357), R.drawable.f357));
        films.add(new Film(context.getString(R.string.s358), R.drawable.f358));
        films.add(new Film(context.getString(R.string.s359), R.drawable.f359));
        films.add(new Film(context.getString(R.string.s360), R.drawable.f360));
        films.add(new Film(context.getString(R.string.s361), R.drawable.f361));
        films.add(new Film(context.getString(R.string.s362), R.drawable.f362));
        films.add(new Film(context.getString(R.string.s363), R.drawable.f363));
        films.add(new Film(context.getString(R.string.s364), R.drawable.f364));
        films.add(new Film(context.getString(R.string.s365), R.drawable.f365));
        films.add(new Film(context.getString(R.string.s366), R.drawable.f366));
        films.add(new Film(context.getString(R.string.s367), R.drawable.f367));
        films.add(new Film(context.getString(R.string.s368), R.drawable.f368));
        films.add(new Film(context.getString(R.string.s369), R.drawable.f369));
        films.add(new Film(context.getString(R.string.s370), R.drawable.f370));
        films.add(new Film(context.getString(R.string.s371), R.drawable.f371));
        films.add(new Film(context.getString(R.string.s372), R.drawable.f372));
        films.add(new Film(context.getString(R.string.s373), R.drawable.f373));
        films.add(new Film(context.getString(R.string.s374), R.drawable.f374));
        films.add(new Film(context.getString(R.string.s375), R.drawable.f375));
        films.add(new Film(context.getString(R.string.s376), R.drawable.f376));
        films.add(new Film(context.getString(R.string.s377), R.drawable.f377));
        films.add(new Film(context.getString(R.string.s378), R.drawable.f378));
        films.add(new Film(context.getString(R.string.s379), R.drawable.f379));
        films.add(new Film(context.getString(R.string.s380), R.drawable.f380));
        films.add(new Film(context.getString(R.string.s381), R.drawable.f381));
        films.add(new Film(context.getString(R.string.s382), R.drawable.f382));
        films.add(new Film(context.getString(R.string.s383), R.drawable.f383));
        films.add(new Film(context.getString(R.string.s384), R.drawable.f384));
        films.add(new Film(context.getString(R.string.s385), R.drawable.f385));
        films.add(new Film(context.getString(R.string.s386), R.drawable.f386));
        films.add(new Film(context.getString(R.string.s387), R.drawable.f387));
        films.add(new Film(context.getString(R.string.s388), R.drawable.f388));
        films.add(new Film(context.getString(R.string.s389), R.drawable.f389));
        films.add(new Film(context.getString(R.string.s390), R.drawable.f390));
        films.add(new Film(context.getString(R.string.s391), R.drawable.f391));
        films.add(new Film(context.getString(R.string.s392), R.drawable.f392));
        films.add(new Film(context.getString(R.string.s393), R.drawable.f393));
        films.add(new Film(context.getString(R.string.s394), R.drawable.f394));
        films.add(new Film(context.getString(R.string.s395), R.drawable.f395));
        films.add(new Film(context.getString(R.string.s396), R.drawable.f396));
        films.add(new Film(context.getString(R.string.s397), R.drawable.f397));
        films.add(new Film(context.getString(R.string.s398), R.drawable.f398));
        films.add(new Film(context.getString(R.string.s399), R.drawable.f399));
        films.add(new Film(context.getString(R.string.s400), R.drawable.f400));
        films.add(new Film(context.getString(R.string.s401), R.drawable.f401));
        films.add(new Film(context.getString(R.string.s402), R.drawable.f402));
        films.add(new Film(context.getString(R.string.s403), R.drawable.f403));
        films.add(new Film(context.getString(R.string.s404), R.drawable.f404));
        films.add(new Film(context.getString(R.string.s405), R.drawable.f405));
        films.add(new Film(context.getString(R.string.s406), R.drawable.f406));
        films.add(new Film(context.getString(R.string.s407), R.drawable.f407));
        films.add(new Film(context.getString(R.string.s408), R.drawable.f408));
        films.add(new Film(context.getString(R.string.s409), R.drawable.f409));
        films.add(new Film(context.getString(R.string.s410), R.drawable.f410));
        films.add(new Film(context.getString(R.string.s411), R.drawable.f411));
        films.add(new Film(context.getString(R.string.s412), R.drawable.f412));
        films.add(new Film(context.getString(R.string.s413), R.drawable.f413));
        films.add(new Film(context.getString(R.string.s414), R.drawable.f414));
        films.add(new Film(context.getString(R.string.s415), R.drawable.f415));
        films.add(new Film(context.getString(R.string.s416), R.drawable.f416));
        films.add(new Film(context.getString(R.string.s417), R.drawable.f417));
        films.add(new Film(context.getString(R.string.s418), R.drawable.f418));
        films.add(new Film(context.getString(R.string.s419), R.drawable.f419));
        films.add(new Film(context.getString(R.string.s420), R.drawable.f420));
        films.add(new Film(context.getString(R.string.s421), R.drawable.f421));
        films.add(new Film(context.getString(R.string.s422), R.drawable.f422));
        films.add(new Film(context.getString(R.string.s423), R.drawable.f423));
        films.add(new Film(context.getString(R.string.s424), R.drawable.f424));
        films.add(new Film(context.getString(R.string.s425), R.drawable.f425));
        films.add(new Film(context.getString(R.string.s426), R.drawable.f426));
        films.add(new Film(context.getString(R.string.s427), R.drawable.f427));
        films.add(new Film(context.getString(R.string.s428), R.drawable.f428));
        films.add(new Film(context.getString(R.string.s429), R.drawable.f429));
        films.add(new Film(context.getString(R.string.s430), R.drawable.f430));
        films.add(new Film(context.getString(R.string.s431), R.drawable.f431));
        films.add(new Film(context.getString(R.string.s432), R.drawable.f432));
        films.add(new Film(context.getString(R.string.s433), R.drawable.f433));
        films.add(new Film(context.getString(R.string.s434), R.drawable.f434));
        films.add(new Film(context.getString(R.string.s435), R.drawable.f435));
        films.add(new Film(context.getString(R.string.s436), R.drawable.f436));
        films.add(new Film(context.getString(R.string.s437), R.drawable.f437));
        films.add(new Film(context.getString(R.string.s438), R.drawable.f438));
        films.add(new Film(context.getString(R.string.s439), R.drawable.f439));
        films.add(new Film(context.getString(R.string.s440), R.drawable.f440));
        films.add(new Film(context.getString(R.string.s441), R.drawable.f441));
        films.add(new Film(context.getString(R.string.s442), R.drawable.f442));
        films.add(new Film(context.getString(R.string.s443), R.drawable.f443));
        films.add(new Film(context.getString(R.string.s444), R.drawable.f444));
        films.add(new Film(context.getString(R.string.s445), R.drawable.f445));
        films.add(new Film(context.getString(R.string.s446), R.drawable.f446));
        films.add(new Film(context.getString(R.string.s447), R.drawable.f447));
        films.add(new Film(context.getString(R.string.s448), R.drawable.f448));
        films.add(new Film(context.getString(R.string.s449), R.drawable.f449));
        films.add(new Film(context.getString(R.string.s450), R.drawable.f450));
        films.add(new Film(context.getString(R.string.s451), R.drawable.f451));
        films.add(new Film(context.getString(R.string.s452), R.drawable.f452));
        films.add(new Film(context.getString(R.string.s453), R.drawable.f453));
        films.add(new Film(context.getString(R.string.s454), R.drawable.f454));
        films.add(new Film(context.getString(R.string.s455), R.drawable.f455));
        films.add(new Film(context.getString(R.string.s456), R.drawable.f456));
        films.add(new Film(context.getString(R.string.s457), R.drawable.f457));
        films.add(new Film(context.getString(R.string.s458), R.drawable.f458));
        films.add(new Film(context.getString(R.string.s459), R.drawable.f459));
        films.add(new Film(context.getString(R.string.s460), R.drawable.f460));
        films.add(new Film(context.getString(R.string.s461), R.drawable.f461));
        films.add(new Film(context.getString(R.string.s462), R.drawable.f462));
        films.add(new Film(context.getString(R.string.s463), R.drawable.f463));
        films.add(new Film(context.getString(R.string.s464), R.drawable.f464));
        films.add(new Film(context.getString(R.string.s465), R.drawable.f465));
        films.add(new Film(context.getString(R.string.s466), R.drawable.f466));
        films.add(new Film(context.getString(R.string.s467), R.drawable.f467));
        films.add(new Film(context.getString(R.string.s468), R.drawable.f468));
        films.add(new Film(context.getString(R.string.s469), R.drawable.f469));
        films.add(new Film(context.getString(R.string.s470), R.drawable.f470));
        films.add(new Film(context.getString(R.string.s471), R.drawable.f471));
        films.add(new Film(context.getString(R.string.s472), R.drawable.f472));
        films.add(new Film(context.getString(R.string.s473), R.drawable.f473));
        films.add(new Film(context.getString(R.string.s474), R.drawable.f474));
        films.add(new Film(context.getString(R.string.s475), R.drawable.f475));
        films.add(new Film(context.getString(R.string.s476), R.drawable.f476));
        films.add(new Film(context.getString(R.string.s477), R.drawable.f477));
        films.add(new Film(context.getString(R.string.s478), R.drawable.f478));
        films.add(new Film(context.getString(R.string.s479), R.drawable.f479));
        films.add(new Film(context.getString(R.string.s480), R.drawable.f480));
        films.add(new Film(context.getString(R.string.s481), R.drawable.f481));
        films.add(new Film(context.getString(R.string.s482), R.drawable.f482));
        films.add(new Film(context.getString(R.string.s483), R.drawable.f483));
        films.add(new Film(context.getString(R.string.s484), R.drawable.f484));
        films.add(new Film(context.getString(R.string.s485), R.drawable.f485));
        films.add(new Film(context.getString(R.string.s486), R.drawable.f486));
        films.add(new Film(context.getString(R.string.s487), R.drawable.f487));
        films.add(new Film(context.getString(R.string.s488), R.drawable.f488));
        films.add(new Film(context.getString(R.string.s489), R.drawable.f489));
        films.add(new Film(context.getString(R.string.s490), R.drawable.f490));
        films.add(new Film(context.getString(R.string.s491), R.drawable.f491));
        films.add(new Film(context.getString(R.string.s492), R.drawable.f492));
        films.add(new Film(context.getString(R.string.s493), R.drawable.f493));
        films.add(new Film(context.getString(R.string.s494), R.drawable.f494));
        films.add(new Film(context.getString(R.string.s495), R.drawable.f495));
        films.add(new Film(context.getString(R.string.s496), R.drawable.f496));
        films.add(new Film(context.getString(R.string.s497), R.drawable.f497));
        films.add(new Film(context.getString(R.string.s498), R.drawable.f498));
        films.add(new Film(context.getString(R.string.s499), R.drawable.f499));
        films.add(new Film(context.getString(R.string.s500), R.drawable.f500));
        films.add(new Film(context.getString(R.string.s501), R.drawable.f501));
        films.add(new Film(context.getString(R.string.s502), R.drawable.f502));
        films.add(new Film(context.getString(R.string.s503), R.drawable.f503));
        films.add(new Film(context.getString(R.string.s504), R.drawable.f504));
        films.add(new Film(context.getString(R.string.s505), R.drawable.f505));
        films.add(new Film(context.getString(R.string.s506), R.drawable.f506));
        films.add(new Film(context.getString(R.string.s507), R.drawable.f507));
        films.add(new Film(context.getString(R.string.s508), R.drawable.f508));
        films.add(new Film(context.getString(R.string.s509), R.drawable.f509));
        films.add(new Film(context.getString(R.string.s510), R.drawable.f510));
        films.add(new Film(context.getString(R.string.s511), R.drawable.f511));
        films.add(new Film(context.getString(R.string.s512), R.drawable.f512));
        films.add(new Film(context.getString(R.string.s513), R.drawable.f513));
        films.add(new Film(context.getString(R.string.s514), R.drawable.f514));
        films.add(new Film(context.getString(R.string.s515), R.drawable.f515));
        films.add(new Film(context.getString(R.string.s516), R.drawable.f516));
        films.add(new Film(context.getString(R.string.s517), R.drawable.f517));
        films.add(new Film(context.getString(R.string.s518), R.drawable.f518));
        films.add(new Film(context.getString(R.string.s519), R.drawable.f519));
        films.add(new Film(context.getString(R.string.s520), R.drawable.f520));
        films.add(new Film(context.getString(R.string.s521), R.drawable.f521));
        films.add(new Film(context.getString(R.string.s522), R.drawable.f522));
        films.add(new Film(context.getString(R.string.s523), R.drawable.f523));
        films.add(new Film(context.getString(R.string.s524), R.drawable.f524));
        films.add(new Film(context.getString(R.string.s525), R.drawable.f525));
        films.add(new Film(context.getString(R.string.s526), R.drawable.f526));
        films.add(new Film(context.getString(R.string.s527), R.drawable.f527));
        films.add(new Film(context.getString(R.string.s528), R.drawable.f528));
        films.add(new Film(context.getString(R.string.s529), R.drawable.f529));
        films.add(new Film(context.getString(R.string.s530), R.drawable.f530));
        films.add(new Film(context.getString(R.string.s531), R.drawable.f531));
        films.add(new Film(context.getString(R.string.s532), R.drawable.f532));
        films.add(new Film(context.getString(R.string.s533), R.drawable.f533));
        films.add(new Film(context.getString(R.string.s534), R.drawable.f534));
        films.add(new Film(context.getString(R.string.s535), R.drawable.f535));
        films.add(new Film(context.getString(R.string.s536), R.drawable.f536));
        films.add(new Film(context.getString(R.string.s537), R.drawable.f537));
        films.add(new Film(context.getString(R.string.s538), R.drawable.f538));
        films.add(new Film(context.getString(R.string.s539), R.drawable.f539));
        films.add(new Film(context.getString(R.string.s540), R.drawable.f540));
        films.add(new Film(context.getString(R.string.s541), R.drawable.f541));
        films.add(new Film(context.getString(R.string.s542), R.drawable.f542));
        films.add(new Film(context.getString(R.string.s543), R.drawable.f543));
        films.add(new Film(context.getString(R.string.s544), R.drawable.f544));
        films.add(new Film(context.getString(R.string.s545), R.drawable.f545));
        films.add(new Film(context.getString(R.string.s546), R.drawable.f546));
        films.add(new Film(context.getString(R.string.s547), R.drawable.f547));
        films.add(new Film(context.getString(R.string.s548), R.drawable.f548));
        films.add(new Film(context.getString(R.string.s549), R.drawable.f549));
        films.add(new Film(context.getString(R.string.s550), R.drawable.f550));
        films.add(new Film(context.getString(R.string.s551), R.drawable.f551));
        films.add(new Film(context.getString(R.string.s552), R.drawable.f552));
        films.add(new Film(context.getString(R.string.s553), R.drawable.f553));
        films.add(new Film(context.getString(R.string.s554), R.drawable.f554));
        films.add(new Film(context.getString(R.string.s555), R.drawable.f555));
        films.add(new Film(context.getString(R.string.s556), R.drawable.f556));
        films.add(new Film(context.getString(R.string.s557), R.drawable.f557));
        films.add(new Film(context.getString(R.string.s558), R.drawable.f558));
        films.add(new Film(context.getString(R.string.s559), R.drawable.f559));
        films.add(new Film(context.getString(R.string.s560), R.drawable.f560));
        films.add(new Film(context.getString(R.string.s561), R.drawable.f561));
        films.add(new Film(context.getString(R.string.s562), R.drawable.f562));
        films.add(new Film(context.getString(R.string.s563), R.drawable.f563));
        films.add(new Film(context.getString(R.string.s564), R.drawable.f564));
        films.add(new Film(context.getString(R.string.s565), R.drawable.f565));
        films.add(new Film(context.getString(R.string.s566), R.drawable.f566));
        films.add(new Film(context.getString(R.string.s567), R.drawable.f567));
        films.add(new Film(context.getString(R.string.s568), R.drawable.f568));
        films.add(new Film(context.getString(R.string.s569), R.drawable.f569));
        films.add(new Film(context.getString(R.string.s570), R.drawable.f570));
        films.add(new Film(context.getString(R.string.s571), R.drawable.f571));
        films.add(new Film(context.getString(R.string.s572), R.drawable.f572));
        films.add(new Film(context.getString(R.string.s573), R.drawable.f573));
        films.add(new Film(context.getString(R.string.s574), R.drawable.f574));
        films.add(new Film(context.getString(R.string.s575), R.drawable.f575));
        films.add(new Film(context.getString(R.string.s576), R.drawable.f576));
        films.add(new Film(context.getString(R.string.s577), R.drawable.f577));
        films.add(new Film(context.getString(R.string.s578), R.drawable.f578));
        films.add(new Film(context.getString(R.string.s579), R.drawable.f579));
        films.add(new Film(context.getString(R.string.s580), R.drawable.f580));
        films.add(new Film(context.getString(R.string.s581), R.drawable.f581));
        films.add(new Film(context.getString(R.string.s582), R.drawable.f582));
        films.add(new Film(context.getString(R.string.s583), R.drawable.f583));
        films.add(new Film(context.getString(R.string.s584), R.drawable.f584));
        films.add(new Film(context.getString(R.string.s585), R.drawable.f585));
        films.add(new Film(context.getString(R.string.s586), R.drawable.f586));
        films.add(new Film(context.getString(R.string.s587), R.drawable.f587));
        films.add(new Film(context.getString(R.string.s588), R.drawable.f588));
        films.add(new Film(context.getString(R.string.s589), R.drawable.f589));
        films.add(new Film(context.getString(R.string.s590), R.drawable.f590));
        films.add(new Film(context.getString(R.string.s591), R.drawable.f591));
        films.add(new Film(context.getString(R.string.s592), R.drawable.f592));
        films.add(new Film(context.getString(R.string.s593), R.drawable.f593));
        films.add(new Film(context.getString(R.string.s594), R.drawable.f594));
        films.add(new Film(context.getString(R.string.s595), R.drawable.f595));
        films.add(new Film(context.getString(R.string.s596), R.drawable.f596));
        films.add(new Film(context.getString(R.string.s597), R.drawable.f597));
        films.add(new Film(context.getString(R.string.s598), R.drawable.f598));
        films.add(new Film(context.getString(R.string.s599), R.drawable.f599));
        films.add(new Film(context.getString(R.string.s600), R.drawable.f600));
    }


}

import 'package:flutter/material.dart';
import 'package:mkwanja/constant/constants.dart';

ThemeData mkwanja() {
  final ThemeData base = ThemeData.light();
  return base.copyWith(
    primaryColor: kPrimaryColor,
    textTheme: const TextTheme(
      headline1: TextStyle(
        fontFamily: "Montserrat",
        fontWeight: FontWeight.bold,
        fontSize: 32,
      ),
      headline2: TextStyle(
        fontFamily: "Montserrat",
        fontWeight: FontWeight.bold,
        fontSize: 34,
      ),
      headline3: TextStyle(
        fontFamily: "Monteserrat",
        fontWeight: FontWeight.bold,
        fontSize: 28,
      ),
      bodyText1: TextStyle(
        fontFamily: "Monteserrat",
        fontSize: 18,
      ),
      bodyText2: TextStyle(
        fontFamily: "Monteserrat",
        color: kContentDarkTheme,
        fontSize: 16,
      ),
      headline5: TextStyle(
        fontFamily: "Monteserrat",
        color: kContentDarkTheme,
        fontSize: 18,
      ),
      headline6: TextStyle(
        fontFamily: "Monteserrat",
        color: kContentDarkTheme,
        // fontWeight: FontWeight.bold,
        fontSize: 14,
      ),
    ),
    canvasColor: kContentColorLightTheme,
    // primaryColor: Colors.red,
  );
}
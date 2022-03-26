import 'package:flutter/material.dart';
import 'package:mkwanja/screen/welcome.dart';
import 'package:mkwanja/theme/theme.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      theme: mkwanja(),
      home: const WelcomeScreen(),
    );
  }
}
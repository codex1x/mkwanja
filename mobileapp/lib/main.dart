import 'package:flutter/material.dart';
import 'package:mkwanja/screen/onboarding_screen/onboarding.dart';
import 'package:mkwanja/screen/registration/register.dart';
import 'package:mkwanja/screen/registration/verifyaccount.dart';
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
      routes: {
        OnboardingScreen.id: (context) => OnboardingScreen(),
        RegisterAccount.id: (context) => RegisterAccount(),
        VerifyPin.id: (context) => const VerifyPin(),
      },
    );
  }
}
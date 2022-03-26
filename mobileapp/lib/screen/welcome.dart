import 'package:flutter/material.dart';
import 'package:mkwanja/constant/constants.dart';
import 'package:flutter_svg/flutter_svg.dart';

class WelcomeScreen extends StatelessWidget {
  const WelcomeScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: kPrimaryColor,
      body: Center(
        child: Column(
          mainAxisSize: MainAxisSize.max,
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            const Spacer(
              flex: 2,
            ),
            SvgPicture.asset(
              'assets/images/logo.svg',
              color: kContentDarkTheme,
            ),
            const SizedBox(
              height: 80,
            ),
            TextButton(
              onPressed: () {},
              style: TextButton.styleFrom(
                backgroundColor: kContentDarkTheme,
                padding: const EdgeInsets.fromLTRB(48, 16, 48, 16),
              ),
              child: Text("Let's Get Started"),
            ),
            const Spacer(
              flex: 1,
            ),
          ],
        ),
      ),
    );
  }
}
import 'package:flutter/material.dart';
import 'package:mkwanja/dashboard/home.dart';
import 'package:pinput/pinput.dart';

import '../../constant/constants.dart';

class VerifyPin extends StatelessWidget {
  static String id = "verify Screen";
  const VerifyPin({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Padding(
        padding: const EdgeInsets.fromLTRB(15, 90, 15, 90),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.start,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text("Verify Account",
                style: Theme.of(context)
                    .textTheme
                    .headline1!
                    .copyWith(color: kPrimaryColor)),
            const SizedBox(
              height: 60,
            ),
            Pinput(),
            const SizedBox(
              height: 60,
            ),
            Text(
                "This season will end in 60 seconds Did’t get code? Recent code",
                style: Theme.of(context)
                    .textTheme
                    .bodyText2!
                    .copyWith(color: kPrimaryColor)),
            Spacer(),
            TextButton(
              onPressed: () {
                Navigator.pushNamed(context, HomeDashboard.id);
              },
              style: TextButton.styleFrom(
                backgroundColor: kPrimaryColor,
                padding: const EdgeInsets.all(20),
              ),
              child: Row(
                children: [
                  Text(
                    "Continue",
                    style: Theme.of(context).textTheme.headline5,
                  ),
                  const Padding(padding: EdgeInsets.fromLTRB(20, 0, 0, 0)),
                  const Icon(
                    Icons.arrow_forward_ios_rounded,
                    color: kContentDarkTheme,
                    size: 16,
                  ),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}
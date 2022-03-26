import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:mkwanja/constant/constants.dart';
import 'package:intl_phone_field/intl_phone_field.dart';
import 'package:mkwanja/screen/registration/verifyaccount.dart';

class RegisterAccount extends StatefulWidget {
  const RegisterAccount({Key? key}) : super(key: key);
  static String id = "Register Screen";

  @override
  State<RegisterAccount> createState() => _RegisterAccountState();
}

class _RegisterAccountState extends State<RegisterAccount> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Padding(
        padding: const EdgeInsets.fromLTRB(15, 90, 15, 90),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.start,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text("Registration",
                style: Theme.of(context)
                    .textTheme
                    .headline1!
                    .copyWith(color: kPrimaryColor)),
            const SizedBox(
              height: 60,
            ),
            Text(
                "Please enter your valid phone number. We shall send you 4 digit code to verify account",
                style: Theme.of(context)
                    .textTheme
                    .bodyText2!
                    .copyWith(color: kPrimaryColor)),
            const SizedBox(
              height: 60,
            ),
            IntlPhoneField(
              decoration: const InputDecoration(
                labelText: 'Phone Number',
                border: OutlineInputBorder(
                  borderSide: BorderSide(),
                ),
              ),
              initialCountryCode: 'TZ',
              onChanged: (phone) {
                print(phone.completeNumber);
              },
            ),
            const SizedBox(
              height: 48,
            ),
            TextButton(
              onPressed: () {
                Navigator.pushNamed(context, VerifyPin.id);
              },
              style: TextButton.styleFrom(
                backgroundColor: kPrimaryColor,
                padding: const EdgeInsets.all(20),
              ),
              child: Row(
                children: [
                  Text(
                    "sign up my account",
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
            const SizedBox(
              height: 24,
            ),
            OutlinedButton(
              onPressed: () {
                // Navigator.pushNamed(context, RegisterUserAgent.id);
              },
              style: TextButton.styleFrom(
                  padding: EdgeInsets.all(20),
                  side: BorderSide(color: Colors.white)),
              child: Row(
                children: [
                  Text(
                    "Have account? sign in",
                    style: Theme.of(context)
                        .textTheme
                        .headline5!
                        .copyWith(color: kPrimaryColor),
                  ),
                  const Padding(padding: EdgeInsets.fromLTRB(20, 0, 0, 0)),
                  const Icon(
                    Icons.arrow_forward_ios_rounded,
                    color: kContentDarkTheme,
                    size: 16,
                  )
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}
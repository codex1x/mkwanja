import 'package:flutter/material.dart';
import 'package:mkwanja/constant/constants.dart';
import 'package:mkwanja/screen/registration/register.dart';
import 'package:easy_onboarding/easy_onboarding.dart';

class OnboardingScreen extends StatelessWidget {
  OnboardingScreen({Key? key}) : super(key: key);
  static String id = "Onboarding Screen";
  @override
  Widget build(BuildContext context) {
    return EasyOnboarding(
      onStart: () {
        Navigator.pushNamed(context, RegisterAccount.id);
      },
      skipButtonColor: Colors.transparent,
      backButtonColor: kPrimaryColor,
      nextButtonColor: kPrimaryColor,
      backgroundColor: Colors.white,
      indicatorSelectedColor: kPrimaryColor,
      indicatorUnselectedColor: Colors.blueGrey,
      startButtonColor: kPrimaryColor,
      nextButtonIcon: const Icon(
        Icons.arrow_forward_ios,
        color: Colors.white,
      ),
      skipButtonText: Text(
        'Skip',
        style: TextStyle(fontSize: 15.0, color: Colors.blue[900]),
      ),
      startButtonText: const Text(
        'Lets get started',
        style: TextStyle(
          color: Colors.white,
          fontSize: 15.0,
        ),
      ),
      backButtonIcon: const Icon(
        Icons.arrow_back_ios,
        color: Colors.white,
      ),
      children: [
        Container(
          child: Column(
            children: [
              Container(
                child: Image.asset('assets/images/easy.png'),
                height: 180,
              ),
              SizedBox(
                height: 48,
              ),
              Text("Easy to use.",
                  style: Theme.of(context)
                      .textTheme
                      .headline1!
                      .copyWith(color: kPrimaryColor)),
              Text('A new onboarding experience',
                  style: Theme.of(context)
                      .textTheme
                      .bodyText2!
                      .copyWith(color: kPrimaryColor)),
            ],
          ),
        ),
        Container(
          child: Column(
            children: [
              Container(
                child: Image.asset('assets/images/secure.png'),
                height: 180,
              ),
              SizedBox(
                height: 48,
              ),
              Text("Fast and Secured",
                  style: Theme.of(context)
                      .textTheme
                      .headline1!
                      .copyWith(color: kPrimaryColor)),
              Text('A new onboarding experience',
                  style: Theme.of(context)
                      .textTheme
                      .bodyText2!
                      .copyWith(color: kPrimaryColor)),
            ],
          ),
        ),
      ],
    );
  }
}
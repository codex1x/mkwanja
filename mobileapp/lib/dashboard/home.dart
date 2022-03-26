import 'package:flutter/material.dart';
import 'package:google_nav_bar/google_nav_bar.dart';
import 'package:mkwanja/constant/constants.dart';
import 'package:mkwanja/dashboard/profile.dart';
import 'package:mkwanja/dashboard/recent.dart';

class HomeDashboard extends StatefulWidget {
  static String id = "HomeDashboard Screen";
  const HomeDashboard({Key? key}) : super(key: key);

  @override
  State<HomeDashboard> createState() => _HomeDashboardState();
}

int _selectedIndex = 0;
TextStyle optionStyle = TextStyle(fontSize: 30, fontWeight: FontWeight.w600);
List<Widget> _widgetOptions = <Widget>[
  Text(
    'Home',
    style: optionStyle,
  ),
  Text(
    'Likes',
    style: optionStyle,
  ),
  Text(
    'Search',
    style: optionStyle,
  ),
  Text(
    'Profile',
    style: optionStyle,
  ),
];

final List<Widget> _widgetoption = [
  const HomeDashboard(),
  const RecentActities(),
  const Profile(),
];

class _HomeDashboardState extends State<HomeDashboard> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      bottomNavigationBar: Container(
        decoration: BoxDecoration(
          color: Colors.white,
          boxShadow: [
            BoxShadow(
              blurRadius: 20,
              color: Colors.black.withOpacity(.1),
            )
          ],
        ),
        child: SafeArea(
          child: Padding(
            padding: const EdgeInsets.symmetric(horizontal: 15.0, vertical: 8),
            child: GNav(
              rippleColor: Colors.grey[300]!,
              hoverColor: Colors.grey[100]!,
              gap: 8,
              activeColor: Colors.black,
              iconSize: 24,
              padding: EdgeInsets.symmetric(horizontal: 20, vertical: 12),
              duration: Duration(milliseconds: 400),
              tabBackgroundColor: Colors.grey[100]!,
              color: Colors.black,
              tabs: const [
                GButton(
                  icon: Icons.home,
                  text: 'Home',
                ),
                GButton(
                  icon: Icons.recent_actors,
                  text: 'History',
                ),
                GButton(
                  icon: Icons.person,
                  text: 'Profile',
                ),
              ],
              selectedIndex: _selectedIndex,
              onTabChange: (index) {
                setState(() {
                  _selectedIndex = index;
                });
              },
            ),
          ),
        ),
      ),
      body: Padding(
        padding: const EdgeInsets.fromLTRB(25, 80, 25, 0),
        child: Column(
          children: [
            Row(
              mainAxisAlignment: MainAxisAlignment.start,
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                const CircleAvatar(
                  child: Icon(
                    Icons.person,
                    size: 30,
                  ),
                  radius: 30,
                ),
                const SizedBox(
                  width: 20,
                ),
                Column(
                  mainAxisAlignment: MainAxisAlignment.start,
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                      "Hamis",
                      style: Theme.of(context)
                          .textTheme
                          .headline2!
                          .copyWith(color: kPrimaryColor),
                    ),
                    Text(
                      "Mohamed",
                      style: Theme.of(context)
                          .textTheme
                          .bodyText2!
                          .copyWith(color: kPrimaryColor),
                    ),
                  ],
                )
              ],
            ),
            const SizedBox(
              height: 45,
            ),
            Container(
              width: double.infinity,
              child: Material(
                borderRadius: BorderRadius.circular(10),
                color: kPrimaryColor,
                elevation: 5,
                child: Padding(
                  padding: const EdgeInsets.all(18),
                  child: Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: Column(
                      mainAxisAlignment: MainAxisAlignment.start,
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text("Current Amount Due"),
                        Text(
                          "1,000,000",
                          style: Theme.of(context).textTheme.headline1,
                        ),
                        const SizedBox(
                          height: 45,
                        ),
                        Row(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: [
                            Column(
                              crossAxisAlignment: CrossAxisAlignment.start,
                              children: [
                                Text(
                                  "Repayment Date",
                                  style: Theme.of(context)
                                      .textTheme
                                      .bodyText2!
                                      .copyWith(fontSize: 8),
                                ),
                                Text("2022-02-02"),
                              ],
                            ),
                            Column(
                              crossAxisAlignment: CrossAxisAlignment.start,
                              children: [
                                Text(
                                  "Repayment Amount",
                                  style: Theme.of(context)
                                      .textTheme
                                      .bodyText2!
                                      .copyWith(fontSize: 8),
                                ),
                                Text("200,000 Tsh"),
                              ],
                            )
                          ],
                        ),
                        const SizedBox(
                          height: 45,
                        ),
                        TextButton(
                          onPressed: () {
                            // Navigator.pushNamed(context, RegisterUserAgent.id);
                          },
                          style: TextButton.styleFrom(
                            backgroundColor: kContentDarkTheme,
                            padding: const EdgeInsets.all(8),
                          ),
                          child: Text(
                            "Pay Now",
                            textAlign: TextAlign.center,
                            style: Theme.of(context)
                                .textTheme
                                .headline5!
                                .copyWith(color: kPrimaryColor),
                          ),
                        ),
                      ],
                    ),
                  ),
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
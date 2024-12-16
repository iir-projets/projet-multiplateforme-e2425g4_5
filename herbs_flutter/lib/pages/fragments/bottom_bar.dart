// footer_bar.dart
import 'package:flutter/material.dart';

class FooterBar extends StatelessWidget {
  final int currentIndex;
  final Function(int) onTap;

  const FooterBar({
    Key? key,
    required this.currentIndex,
    required this.onTap,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return BottomNavigationBar(
      items: const [
        BottomNavigationBarItem(
          icon: Icon(Icons.home_outlined),
          label: 'Home',
        ),
        BottomNavigationBarItem(
          icon: Icon(Icons.newspaper_sharp),
          label: 'Articles',
        ),
        BottomNavigationBarItem(
          icon: Icon(Icons.yard_outlined),
          label: 'Herbs',
        ),
        BottomNavigationBarItem(
          icon: Icon(Icons.favorite_border),
          label: 'Favorites',
        ),
        BottomNavigationBarItem(
          icon: Icon(Icons.person_outline),
          label: 'Profile',
        ),
        BottomNavigationBarItem(
          icon: Icon(Icons.notifications_none),
          label: 'Notifications',
        ),
      ],
      currentIndex: currentIndex,
      selectedItemColor: const Color(0xFF90A955),
      unselectedItemColor: Colors.grey,
      showUnselectedLabels: true,
      type: BottomNavigationBarType.fixed,
      onTap: onTap,
    );
  }
}

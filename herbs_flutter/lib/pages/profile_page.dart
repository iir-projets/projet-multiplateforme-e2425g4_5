import 'package:flutter/material.dart';
import 'package:herbs_flutter/pages/base_layout.dart';

class ProfilePage extends StatelessWidget {
  const ProfilePage({super.key});

  @override
  Widget build(BuildContext context) {
    return BaseLayout(
      currentIndex: 4,
      child: Center(
        child: Text('Profile Page'),
      ),
    );
  }
}
import 'package:flutter/material.dart';

class BannerWidget extends StatelessWidget {
  const BannerWidget({super.key});

  @override
  Widget build(BuildContext context) {
    return Image.asset(
      'assets/pic_accueil.jpg',
      height: 150,
      fit: BoxFit.cover,
    );
  }
}
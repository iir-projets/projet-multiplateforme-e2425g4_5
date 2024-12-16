import 'package:flutter/material.dart';
import 'package:herbs_flutter/pages/fragments/bottom_bar.dart';


class BaseLayout extends StatefulWidget {
  final Widget child;
  final int currentIndex;

  const BaseLayout({
    Key? key,
    required this.child,
    required this.currentIndex,
  }) : super(key: key);

  @override
  _BaseLayoutState createState() => _BaseLayoutState();
}

class _BaseLayoutState extends State<BaseLayout> {
  late int _currentIndex;

  @override
  void initState() {
    super.initState();
    _currentIndex = widget.currentIndex;
  }

  void _onItemTapped(int index) {
    setState(() {
      _currentIndex = index;
    });

    switch (index) {
      case 0:
        Navigator.pushReplacementNamed(context, '/home');
        break;
      case 1:
        Navigator.pushReplacementNamed(context, '/articles');
        break;
      case 2:
        Navigator.pushReplacementNamed(context, '/herbs');
        break;
      case 3:
        Navigator.pushReplacementNamed(context, '/favorites');
        break;
      case 4:
        Navigator.pushReplacementNamed(context, '/profile');
        break;
      case 5:
        Navigator.pushReplacementNamed(context, '/notifications');
        break;
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: widget.child,
      bottomNavigationBar: FooterBar(
        currentIndex: _currentIndex,
        onTap: _onItemTapped,
      ),
    );
  }
}
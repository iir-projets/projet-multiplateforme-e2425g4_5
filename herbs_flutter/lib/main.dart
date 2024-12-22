import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/material.dart';
import 'package:herbs_flutter/authentication/aut_firebase.dart';
import 'package:herbs_flutter/authentication/register.dart';
import 'package:herbs_flutter/firebase_options.dart';
import 'package:herbs_flutter/pages/articles/articles_page.dart';
import 'package:herbs_flutter/pages/herbs/favoris_page.dart';
import 'package:herbs_flutter/pages/home_page.dart';
import 'package:herbs_flutter/pages/notifications/notifications_page.dart';
import 'package:herbs_flutter/pages/profile_page.dart';
import 'package:herbs_flutter/pages/herbs/herbs_page.dart';
import 'package:firebase_ui_auth/firebase_ui_auth.dart' as firebase_ui;

Future<void> main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp(options: DefaultFirebaseOptions.currentPlatform);
  firebase_ui.FirebaseUIAuth.configureProviders([
    firebase_ui.EmailAuthProvider(),
  ]);
  runApp(const HerbGuideApp());
}

class HerbGuideApp extends StatelessWidget {
  const HerbGuideApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Herb Guide',
      theme: ThemeData(
        primarySwatch: Colors.green,
        fontFamily: 'Roboto',
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: const SplashScreen(),
      routes: {
        '/home': (context) => const HomePage(),
        '/articles': (context) => const ArticlesPage(),
        '/herbs': (context) => const HerbsPage(),
        '/favorites': (context) => const FavorisPage(),
        '/profile': (context) => const ProfilePage(),
        '/notifications': (context) => const NotificationsPage(),
        '/login': (context) => const AutFirebase(),
        '/register': (context) => const RegisterPage(),
      },
    );
  }
}

class SplashScreen extends StatelessWidget {
  const SplashScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        fit: StackFit.expand,
        children: [
          // Background Image
          Image.asset(
            'assets/explore.jpg',
            fit: BoxFit.cover,
          ),
          // Content
          Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              // Centered Logo
              Image.asset(
                'assets/logo_plante.png',
                width: 150,
                height: 150,
              ),
              const SizedBox(height: 40),
              // Start Exploring Button
              ElevatedButton(
                onPressed: () {
                  Navigator.of(context).pushReplacementNamed('/login');
                },
                style: ElevatedButton.styleFrom(
                  backgroundColor: const Color(0xFFCFE1D3),
                  foregroundColor: const Color(0xFF2C5530),
                  padding: const EdgeInsets.symmetric(horizontal: 24, vertical: 12),
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(25),
                  ),
                ),
                child: const Text(
                  'Start exploring now!',
                  style: TextStyle(
                    fontSize: 16,
                    fontWeight: FontWeight.w500,
                  ),
                ),
              ),
            ],
          ),
        ],
      ),
    );
  }
}
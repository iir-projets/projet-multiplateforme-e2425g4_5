import 'package:flutter/material.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:herbs_flutter/authentication/login.dart';
import 'package:herbs_flutter/pages/home_page.dart';

class AutFirebase extends StatelessWidget {
  const AutFirebase({super.key});

  @override
  Widget build(BuildContext context) {
    return StreamBuilder<User?>(
      stream: FirebaseAuth.instance.authStateChanges(),
      builder: (context, snapshot) {
        // Si l'utilisateur n'est pas connecté
        if (!snapshot.hasData) {
          return LoginPage(); // Votre écran de connexion
        }

        // L'utilisateur est connecté
        // final user = snapshot.data!;
        return const HomePage();
      },
    );
  }
}
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:herbs_flutter/authentication/register.dart';
//import 'package:herbs_flutter/pages/home_page.dart';

class LoginPage extends StatefulWidget {
  const LoginPage({super.key});

  @override
  _LoginPageState createState() => _LoginPageState();
}


class _LoginPageState extends State<LoginPage> {

  final _emailController = TextEditingController();
  final _passwordController = TextEditingController();
  final _auth = FirebaseAuth.instance;
  String _errorMessage = '';

  Future<void> _signIn() async {
    try {
      await _auth.signInWithEmailAndPassword(
        email: _emailController.text.trim(),
        password: _passwordController.text.trim(),
      );
    } catch (e) {
      setState(() {
        _errorMessage = e.toString();
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        decoration: const BoxDecoration(
          image: DecorationImage(
            image: AssetImage('assets/plante1.jpg'),
            fit: BoxFit.cover,
          ),
        ),
        child: Center(
          child: SingleChildScrollView(
            child: Container(
              margin: const EdgeInsets.symmetric(horizontal: 24),
              padding: const EdgeInsets.all(24),
              decoration: BoxDecoration(
                color: const Color(0xFFEDE8DC).withOpacity(0.68), // Pink with opacity
                borderRadius: BorderRadius.circular(20),
              ),
              child: Column(
                mainAxisSize: MainAxisSize.min,
                children: [
                  // Logo
                  Container(
                    width: 150,
                    height: 150,
                    decoration: const BoxDecoration(
                      color: Color(0xFFCFE1D3), // Mint green
                      shape: BoxShape.circle,
                    ),
                    child: Center(
                      child: Image.asset(
                        'assets/logo_plante.png',
                        width: 120,
                        height: 120,
                      ),
                    ),
                  ),
                  const SizedBox(height: 32),
                  // Email Field
                  Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      const Text(
                        'email',
                        style: TextStyle(
                          color: Colors.black87,
                          fontSize: 16,
                        ),
                      ),
                      const SizedBox(height: 8),
                      TextField(
                        controller: _emailController,
                        decoration: InputDecoration(
                          hintText: 'insert your email',
                          hintStyle: TextStyle(
                            color: Colors.black.withOpacity(0.5),
                          ),
                          filled: true,
                          fillColor: Colors.white,
                          border: OutlineInputBorder(
                            borderRadius: BorderRadius.circular(10),
                            borderSide: BorderSide.none,
                          ),
                          contentPadding: const EdgeInsets.symmetric(
                            horizontal: 16,
                            vertical: 12,
                          ),
                        ),
                      ),
                    ],
                  ),
                  const SizedBox(height: 24),
                  // Password Field
                  Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      const Text(
                        'password',
                        style: TextStyle(
                          color: Colors.black87,
                          fontSize: 16,
                        ),
                      ),
                      const SizedBox(height: 8),
                      TextField(
                        controller: _passwordController,
                        obscureText: true,
                        decoration: InputDecoration(
                          hintText: 'insert your password',
                          hintStyle: TextStyle(
                            color: Colors.black.withOpacity(0.5),
                          ),
                          filled: true,
                          fillColor: Colors.white,
                          border: OutlineInputBorder(
                            borderRadius: BorderRadius.circular(10),
                            borderSide: BorderSide.none,
                          ),
                          contentPadding: const EdgeInsets.symmetric(
                            horizontal: 16,
                            vertical: 12,
                          ),
                        ),
                      ),
                    ],
                  ),
                  const SizedBox(height: 32),
                  // Sign In Button
                  SizedBox(
                    width: double.infinity,
                    child: ElevatedButton(
                      onPressed: _signIn/*() {
                        Navigator.of(context).push(
                            MaterialPageRoute(builder: (context) => HomePage()),
                          );
                      }*/,
                      style: ElevatedButton.styleFrom(
                        backgroundColor: const Color(0xFF90A955), // Green color
                        padding: const EdgeInsets.symmetric(vertical: 12),
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(10),
                        ),
                      ),
                      child: const Text(
                        'sing in',
                        style: TextStyle(
                          fontSize: 16,
                          fontWeight: FontWeight.w500,
                        ),
                      ),
                    ),
                    
                  ),
                  if (_errorMessage.isNotEmpty)
                    Padding(
                      padding: const EdgeInsets.only(top: 16),
                      child: Text(
                        _errorMessage,
                        style: const TextStyle(color: Colors.red),
                      ),
                    ),
                  const SizedBox(height: 24),
                  // Bottom Links
                  Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: [
                      TextButton(
                        onPressed: () {
                          // Add forgot password logic
                        },
                        child: const Text(
                          'forgot password',
                          style: TextStyle(
                            color: Colors.black87,
                            fontSize: 14,
                          ),
                        ),
                      ),
                      TextButton(
                        onPressed: () {
                          Navigator.of(context).push(
                            MaterialPageRoute(builder: (context) => RegisterPage()),
                          );
                        },
                        child: const Text(
                          'don\'t have account',
                          style: TextStyle(
                            color: Colors.black87,
                            fontSize: 14,
                          ),
                        ),
                      ),
                    ],
                  ),
                ],
              ),
            ),
          ),
        ),
      ),
    );
  }
}
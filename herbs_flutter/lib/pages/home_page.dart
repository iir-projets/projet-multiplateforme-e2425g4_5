import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:herbs_flutter/pages/base_layout.dart';
import 'package:herbs_flutter/pages/fragments/banner.dart';
import 'package:herbs_flutter/pages/fragments/chatbot_popup.dart';
import 'package:kommunicate_flutter/kommunicate_flutter.dart';

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {

  @override
  Widget build(BuildContext context) {
    return BaseLayout(
      currentIndex: 0,
      child: Stack(
        children: [
          SingleChildScrollView(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.stretch,
              children: [
                const BannerWidget(),
                Padding(
                  padding: const EdgeInsets.all(16.0),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.stretch,
                    children: [
                      Container(
                        padding: const EdgeInsets.all(16),
                        decoration: BoxDecoration(
                          color: const Color(0xFFF5F5F5),
                          borderRadius: BorderRadius.circular(20),
                        ),
                        child: Column(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: [
                            // Rosemary Card
                            ClipRRect(
                              borderRadius: BorderRadius.circular(15),
                              child: Stack(
                                children: [
                                  Image.asset(
                                    'assets/rosemary.jpg',
                                    height: 200,
                                    width: double.infinity,
                                    fit: BoxFit.cover,
                                  ),
                                  Positioned(
                                    bottom: 10,
                                    left: 10,
                                    child: Text(
                                      'Rosemary',
                                      style: TextStyle(
                                        color: Colors.white,
                                        fontSize: 20,
                                        fontWeight: FontWeight.bold,
                                      ),
                                    ),
                                  ),
                                ],
                              ),
                            ),
                            
                            const SizedBox(height: 16),
                            
                            // Healing Power Text
                            Row(
                              children: [
                                Expanded(
                                  child: Text(
                                    'Harness the healing power of nature.',
                                    textAlign: TextAlign.center,
                                    style: TextStyle(
                                      fontSize: 20,
                                      fontStyle: FontStyle.italic,
                                      fontFamily: 'Istok Web',
                                      fontWeight: FontWeight.w700,
                                    ),
                                  ),
                                ),
                                Image.asset(
                                  'assets/deco.png',
                                  height: 20,
                                ),
                              ],
                            ),
                            
                            const SizedBox(height: 16),
                            
                            // Chamomile Card
                            ClipRRect(
                              borderRadius: BorderRadius.circular(15),
                              child: Stack(
                                children: [
                                  Image.asset(
                                    'assets/camomille.jpg',
                                    height: 200,
                                    width: double.infinity,
                                    fit: BoxFit.cover,
                                  ),
                                  Positioned(
                                    bottom: 10,
                                    left: 10,
                                    child: Text(
                                      'Chamomile',
                                      style: TextStyle(
                                        color: Colors.white,
                                        fontSize: 20,
                                        fontWeight: FontWeight.bold,
                                      ),
                                    ),
                                  ),
                                ],
                              ),
                            ),
                            
                            const SizedBox(height: 16),
                            
                            // Natural Remedies Text
                            Row(
                              children: [
                                Expanded(
                                  child: Text(
                                    'Natural remedies for wellness',
                                    textAlign: TextAlign.center,
                                    style: TextStyle(
                                      fontSize: 20,
                                      fontStyle: FontStyle.italic,
                                      fontFamily: 'Istok Web',
                                      fontWeight: FontWeight.w700,
                                    ),
                                  ),
                                ),
                                Image.asset(
                                  'assets/deco.png',
                                  height: 20,
                                ),
                              ],
                            ),
                            
                            const SizedBox(height: 24),
                            
                            // See More Button
                            Center(
                              child: ElevatedButton(
                                onPressed: () {
                                  // Add navigation logic
                                  Navigator.pushReplacementNamed(context, '/herbs');
                                },
                                style: ElevatedButton.styleFrom(
                                  backgroundColor: const Color(0xFF90A955),
                                  padding: const EdgeInsets.symmetric(
                                    horizontal: 24,
                                    vertical: 12,
                                  ),
                                  shape: RoundedRectangleBorder(
                                    borderRadius: BorderRadius.circular(25),
                                  ),
                                ),
                                child: const Text(
                                  'See more ...',
                                  style: TextStyle(
                                    color: Colors.white,
                                    fontSize: 16,
                                  ),
                                ),
                              ),
                            ),
                          ],
                        ),
                      ),
                    ],
                  ),
                ),
                const SizedBox(height: 80),
              ],
            ),
          ),
          // Chatbot Button
           // Only show if conversation isn't opened yet
            Positioned(
              right: 16,
              bottom: 50,
              child: FloatingActionButton(
                onPressed: () async {
                  // 2219e29d79808e51117c56567f1b67c3a
                  dynamic conversationObject = {
                    'appId': '2219e29d79808e51117c56567f1b67c3a',
                    'popupWidget': false,
                    'isDefaultBotEnabled': false,
                  };

                  KommunicateFlutterPlugin.buildConversation(conversationObject)
                      .then((clientConversationId) {
                    print("Conversation builder success: $clientConversationId");
                  }).catchError((error) {
                    print("Conversation builder error: $error");
                  });
                },

                backgroundColor: const Color.fromARGB(133, 255, 255, 255),
                child: const Icon(
                  Icons.smart_toy,
                  color: Color(0xFF90A955),
                  size: 37.0,
                ),
              ),
            ),
        ],
      ),
    );
  }

}
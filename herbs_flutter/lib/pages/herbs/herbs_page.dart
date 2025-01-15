/// herbs page

import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:flutter/material.dart';
import 'package:herbs_flutter/data/herbs.dart';
import 'package:herbs_flutter/data/users.dart' as user_data;
import 'package:herbs_flutter/pages/base_layout.dart';
import 'package:herbs_flutter/pages/fragments/chatbot_popup.dart';
import 'package:herbs_flutter/pages/herbs/herb_card.dart';
import 'package:herbs_flutter/pages/herbs/herb_detail.dart';
import 'package:herbs_flutter/services/user_service.dart';

class HerbsPage extends StatefulWidget {
  const HerbsPage({super.key});

  @override
  State<HerbsPage> createState() => _HerbsPageState();
}

class _HerbsPageState extends State<HerbsPage> {
  final UserService user_service = UserService();
  final TextEditingController _searchController = TextEditingController();
  List<Herbs> filteredHerbs = [];
  late user_data.Users user;
  bool _isUserInitialized = false;
  FirebaseFirestore db = FirebaseFirestore.instance;
  late List<Herbs> herbsDB;

  
  @override
  void initState() {
    super.initState();
    _initializeUser();
    // Initialize with all herbs
    _initializeHerbs();
    
    
  }

  void _filterHerbs(String query) {
    setState(() {
      filteredHerbs = herbsDB.where((herb) {
        final name = herb.name.toLowerCase();
        final description = herb.description.toLowerCase();
        return name.contains(query.toLowerCase()) ||
            description.contains(query.toLowerCase());
      }).toList();
    });
  }

  void _initializeUser() async{
    

    final userData = await user_service.fetchUserData();
    try{

    
      setState(() {
        user = user_data.Users(
          id: userData.id,
          email: userData.data()['email']??'',
          firstName: userData.data()['firstName']??'',
          lastName: userData.data()['lastName']??'',
          mobile: userData.data()['mobile']??'',
          address: userData.data()['address']??'',
          password: userData.data()['password']??'',
          savedArticles: userData.data()['savedArticles']??'',
          favoredHerbs: userData.data()['favoredHerbs']??'',
          deseases: userData.data()['deseases']??'',
          allergies: userData.data()['allergies']??'',
          medicines: userData.data()['medicines']??'',
          notifications: (userData.data()['notifications'] as List<dynamic>)
            .map((e) => user_data.Notification(
              id: e['id'],
              title: e['title'],
              description: e['description'],
              articleId: e['articleId'],
              imageUrl: e['imageUrl'],
              isRead: e['isRead'],
            ))
            .toList(),
        );
        _isUserInitialized = true;
      });// Initialize user data
    }catch(e){
      print('Error fetching user: $e');
    }
  }
  

  void _initializeHerbs() async{
    final querySnapshot = await db.collection('herbs').get();

    setState(() {
      herbsDB = querySnapshot.docs.map((doc) {
        final data = doc.data();
        return Herbs(
          id: doc.id,
          name: data['name'],
          imageUrl: data['imageUrl'],
          properties: data['properties'],
          precautions: data['precautions'],
          description: data['description'],
          usage: data['usage'],
          healthBenefits: data['healthBenefits'],
          origins: data['origins'],

        );
      }).toList();
    });
    filteredHerbs = herbsDB;// Initialize filtered list
    if(herbsDB.isEmpty) {
      print('No herbs found');
    }

  }

  @override
  Widget build(BuildContext context) {
    if (!_isUserInitialized) {
      return const Center(
        child: CircularProgressIndicator(), // Show loading spinner
      );
    }
    return BaseLayout(
      currentIndex: 2,
      child: Stack(
      children: [
        Column(
          children: [
          // Banner and Search Section
            Container(
              decoration: const BoxDecoration(
                image: DecorationImage(
                  image: AssetImage('assets/pic_accueil.jpg'),
                  fit: BoxFit.cover,
                ),
              ),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  // Logo
                  Padding(
                    padding: const EdgeInsets.all(7.0),
                    child: Image.asset(
                      'assets/logo_plante.png',
                      height: 55,
                    ),
                  ),
                  // Search Bar and Save Button
                  Padding(
                    padding: const EdgeInsets.all(5.0),
                    child: Row(
                      children: [
                        Expanded(
                          child: Container(
                            decoration: BoxDecoration(
                              color: Colors.white,
                              borderRadius: BorderRadius.circular(25),
                            ),
                            child: TextField(
                              controller: _searchController,
                              onChanged: _filterHerbs,
                              decoration: InputDecoration(
                                hintText: 'Search herbs',
                                prefixIcon: const Icon(Icons.search),
                                border: OutlineInputBorder(
                                  borderRadius: BorderRadius.circular(25),
                                  borderSide: BorderSide.none,
                                ),
                                filled: true,
                                fillColor: Colors.white,
                              ),
                            ),
                          ),
                        ),
                        const SizedBox(width: 8),
                      ],
                    ),
                  ),
                ],
            ),
          ),
          // herbs List
            Expanded(
              child: GridView.builder(
                padding: const EdgeInsets.all(16),
                gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
                  crossAxisCount: 2, // Number of columns
                  childAspectRatio: 0.75, // Adjust the aspect ratio for card height
                  crossAxisSpacing: 16, // Space between columns
                  mainAxisSpacing: 16, // Space between rows
                ),
                itemCount: filteredHerbs.length,
                itemBuilder: (context, index) {
                  final herb = filteredHerbs[index];
                  return HerbCard(
                    name: herb.name,
                    imageUrl: herb.imageUrl,
                    saved: user.favoredHerbs.isEmpty?false:user.favoredHerbs.contains(herb.id),
                    onTap: () {
                      Navigator.push(
                        context,
                        MaterialPageRoute(
                          builder: (context) => HerbDetailPage(
                            name: herb.name,
                            imageUrl: herb.imageUrl,
                            properties: herb.properties,
                            precautions: herb.precautions,
                            description: herb.description,
                            usage: herb.usage,
                            healthBenefits: herb.healthBenefits,
                            origins: herb.origins,
                            saved: user.favoredHerbs.isEmpty?false:user.favoredHerbs.contains(herb.id),
                            onSaveToggle: () {
                              setState(() {
                                // Toggle saved status
                                if (index != -1) {
                                  
                                  if (user.favoredHerbs.contains(herb.id)) {
                                    user_service.RemoveFavoriteHerb(herb, user);
                                    //user.favoredHerbs.remove(herb.id);
                                  } else {
                                    user_service.AddFavoriteHerb(herb, user);
                                    //user.favoredHerbs.add(herb.id);
                                  }
                                  _initializeUser();
                                  _initializeHerbs();
                                }
                              });
                            },
                          ),
                        ),
                      );
                    },
                    onSaveToggle: () {
                      setState(() {
                        // Toggle saved status
                        if (index != -1) {
                                  
                          if (user.favoredHerbs.contains(herb.id)) {
                            user_service.RemoveFavoriteHerb(herb, user);
                            //user.favoredHerbs.remove(herb.id);
                          } else {
                            user_service.AddFavoriteHerb(herb, user);
                            //user.favoredHerbs.add(herb.id);
                          }
                          _initializeUser();
                          _initializeHerbs();
                        }

                      });
                    },
                  );
                },
              ),
            ),
        ],
        ),
        Positioned(
            right: 16,
            bottom: 20,
            child: FloatingActionButton(
              onPressed: () {
                _showChatbot(context);
              },
              backgroundColor: const Color.fromARGB(133, 255, 255, 255),
              child: const Icon(
                Icons.smart_toy,
                color: Color(0xFF90A955), // Green button with white icon for better contrast
                size: 37.0,          // Increased icon size
              ),
            ),
          ),
      ]
      )
    );
  }
  void _showChatbot(BuildContext context) {
    showModalBottomSheet(
      context: context,
      isScrollControlled: true,
      backgroundColor: Colors.transparent,
      builder: (context) => const ChatbotOverlay(),
    );
  }
}



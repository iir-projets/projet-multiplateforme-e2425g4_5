/// articles page
/// 
import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:flutter/material.dart';
import 'package:herbs_flutter/data/articles.dart';
import 'package:herbs_flutter/data/users.dart';
import 'package:herbs_flutter/pages/articles/article_card.dart';
import 'package:herbs_flutter/pages/articles/article_details.dart';
import 'package:herbs_flutter/pages/articles/article_saved.dart';
import 'package:herbs_flutter/pages/base_layout.dart';
import 'package:herbs_flutter/pages/fragments/chatbot_popup.dart';
import 'package:herbs_flutter/services/user_service.dart';

class ArticlesPage extends StatefulWidget {
  const ArticlesPage({super.key});

  @override
  State<ArticlesPage> createState() => _ArticlesPageState();
}

class _ArticlesPageState extends State<ArticlesPage> {
  final TextEditingController _searchController = TextEditingController();
  
  List<Articles> filteredArticles = [];
  FirebaseFirestore db = FirebaseFirestore.instance;
  late List<Articles> articlesDB;
  late Users user;
  bool _isUserInitialized = false;
  /*static List<Map<String, dynamic>> articles = [
    {
      'id': 1,
      'title': 'Chamomile: Nature\'s Calming Herb',
      'imageUrl': 'assets/camomille.jpg',
      'content': [
        {
          'title': 'The Origins of Chamomile',
          'text': 'Chamomile has been used for centuries as a natural remedy. It originates from the daisy family and is known for its apple-like scent. Ancient Egyptians dedicated chamomile to their sun god and valued it above all other herbs for its healing qualities.'
        },
        {
          'title': 'Health Benefits',
          'text': 'Chamomile is renowned for its calming effects and ability to reduce anxiety and promote sleep. It\'s also known to aid digestion, reduce inflammation, and boost immune health. Some studies suggest it may even have anti-cancer properties.'
        }
      ],
      'comments': [
        {'user': 'HerbLover', 'text': 'I love chamomile tea before bed. It really helps me relax!'},
        {'user': 'SleepyHead', 'text': 'Great article! I didn\'t know chamomile had so many benefits.'},
        {'user': 'NaturalHealer', 'text': 'Chamomile is my go-to herb for stress relief. Highly recommend!'},
      ],
      'saved': true
    },
    {
      'id': 2,
      'title': 'Rosemary: The Herb of Remembrance',
      'imageUrl': 'assets/rosemary.jpg',
      'content': [
        {
          'title': 'A Brief History',
          'text': 'Rosemary has been used since ancient times for its medicinal properties. It was often used in wedding ceremonies to symbolize love and loyalty. The herb was also believed to improve memory, leading to its nickname "the herb of remembrance".'
        },
        {
          'title': 'Culinary and Medicinal Uses',
          'text': 'In cooking, rosemary adds a delightful flavor to many dishes, especially roasted meats and vegetables. Medicinally, it\'s believed to improve digestion, enhance memory and concentration, and relieve muscle pain and spasms.'
        }
      ],
      'comments': [
        {'user': 'ChefJohn', 'text': 'Rosemary is a staple in my kitchen. It adds such a wonderful flavor to roasted potatoes!'},
        {'user': 'GardenGuru', 'text': 'I grow rosemary in my herb garden. It\'s so easy to maintain and smells amazing.'},
        {'user': 'MemoryMaster', 'text': 'Interesting to learn about rosemary\'s connection to memory. I\'ll have to try it out!'},
      ],
      'saved': false
    },
    {
      'id': 3,
      'title': 'Chamomile: Nature\'s Calming Herb',
      'imageUrl': 'assets/camomille.jpg',
      'content': [
        {
          'title': 'The Origins of Chamomile',
          'text': 'Chamomile has been used for centuries as a natural remedy. It originates from the daisy family and is known for its apple-like scent. Ancient Egyptians dedicated chamomile to their sun god and valued it above all other herbs for its healing qualities.'
        },
        {
          'title': 'Health Benefits',
          'text': 'Chamomile is renowned for its calming effects and ability to reduce anxiety and promote sleep. It\'s also known to aid digestion, reduce inflammation, and boost immune health. Some studies suggest it may even have anti-cancer properties.'
        }
      ],
      'comments': [
        {'user': 'HerbLover', 'text': 'I love chamomile tea before bed. It really helps me relax!'},
        {'user': 'SleepyHead', 'text': 'Great article! I didn\'t know chamomile had so many benefits.'},
        {'user': 'NaturalHealer', 'text': 'Chamomile is my go-to herb for stress relief. Highly recommend!'},
      ],
      'saved': true
    },
    {
      'id': 4,
      'title': 'Rosemary: The Herb of Remembrance',
      'imageUrl': 'assets/rosemary.jpg',
      'content': [
        {
          'title': 'A Brief History',
          'text': 'Rosemary has been used since ancient times for its medicinal properties. It was often used in wedding ceremonies to symbolize love and loyalty. The herb was also believed to improve memory, leading to its nickname "the herb of remembrance".'
        },
        {
          'title': 'Culinary and Medicinal Uses',
          'text': 'In cooking, rosemary adds a delightful flavor to many dishes, especially roasted meats and vegetables. Medicinally, it\'s believed to improve digestion, enhance memory and concentration, and relieve muscle pain and spasms.'
        }
      ],
      'comments': [
        {'user': 'ChefJohn', 'text': 'Rosemary is a staple in my kitchen. It adds such a wonderful flavor to roasted potatoes!'},
        {'user': 'GardenGuru', 'text': 'I grow rosemary in my herb garden. It\'s so easy to maintain and smells amazing.'},
        {'user': 'MemoryMaster', 'text': 'Interesting to learn about rosemary\'s connection to memory. I\'ll have to try it out!'},
      ],
      'saved': false
    },
    {
      'id': 5,
      'title': 'Chamomile: Nature\'s Calming Herb',
      'imageUrl': 'assets/camomille.jpg',
      'content': [
        {
          'title': 'The Origins of Chamomile',
          'text': 'Chamomile has been used for centuries as a natural remedy. It originates from the daisy family and is known for its apple-like scent. Ancient Egyptians dedicated chamomile to their sun god and valued it above all other herbs for its healing qualities.'
        },
        {
          'title': 'Health Benefits',
          'text': 'Chamomile is renowned for its calming effects and ability to reduce anxiety and promote sleep. It\'s also known to aid digestion, reduce inflammation, and boost immune health. Some studies suggest it may even have anti-cancer properties.'
        }
      ],
      'comments': [
        {'user': 'HerbLover', 'text': 'I love chamomile tea before bed. It really helps me relax!'},
        {'user': 'SleepyHead', 'text': 'Great article! I didn\'t know chamomile had so many benefits.'},
        {'user': 'NaturalHealer', 'text': 'Chamomile is my go-to herb for stress relief. Highly recommend!'},
      ],
      'saved': true
    },
    {
      'id': 6,
      'title': 'Rosemary: The Herb of Remembrance',
      'imageUrl': 'assets/rosemary.jpg',
      'content': [
        {
          'title': 'A Brief History',
          'text': 'Rosemary has been used since ancient times for its medicinal properties. It was often used in wedding ceremonies to symbolize love and loyalty. The herb was also believed to improve memory, leading to its nickname "the herb of remembrance".'
        },
        {
          'title': 'Culinary and Medicinal Uses',
          'text': 'In cooking, rosemary adds a delightful flavor to many dishes, especially roasted meats and vegetables. Medicinally, it\'s believed to improve digestion, enhance memory and concentration, and relieve muscle pain and spasms.'
        }
      ],
      'comments': [
        {'user': 'ChefJohn', 'text': 'Rosemary is a staple in my kitchen. It adds such a wonderful flavor to roasted potatoes!'},
        {'user': 'GardenGuru', 'text': 'I grow rosemary in my herb garden. It\'s so easy to maintain and smells amazing.'},
        {'user': 'MemoryMaster', 'text': 'Interesting to learn about rosemary\'s connection to memory. I\'ll have to try it out!'},
      ],
      'saved': false
    },
  ];*/

  @override
  void initState() {
    super.initState();
    _initializeArticles();
    _initializeUser();
  }

  void _initializeArticles() async{
    final querySnapshot = await db.collection('articles').get();

  setState(() {
    articlesDB = querySnapshot.docs.map((doc) {
      final data = doc.data();
      return Articles(
        id: doc.id,
        title: data['title'],
        imageUrl: data['imageUrl'],
        content: (data['content'] as List<dynamic>)
            .map((e) => Content(title: e['title'], text: e['text']))
            .toList(),
        comments: (data['comments'] as List<dynamic>)
            .map((e) => Comments(user: e['user'], text: e['text']))
            .toList(),
      );
    }).toList();
  });
  filteredArticles = articlesDB;// Initialize filtered list
  if(articlesDB.isEmpty) {
    print('No articles found');
  }
}

void _initializeUser() async{
    final UserService user_service = UserService();

  final userData = await user_service.fetchUserData();
  try{

  
    setState(() {
      user = Users(
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
      );
      _isUserInitialized = true;
    });// Initialize user data
  }catch(e){
    print('Error fetching user: $e');
  }
}




  void _filterArticles(String query) {
    setState(() {
      filteredArticles = articlesDB.where((article) {
        final title = article.title.toLowerCase();
        final description = article.content[0].text.toLowerCase();
        return title.contains(query.toLowerCase()) ||
            description.contains(query.toLowerCase());
      }).toList();
    });
  }
  

  @override
  Widget build(BuildContext context) {
    if (!_isUserInitialized) {
      return const Center(
        child: CircularProgressIndicator(), // Show loading spinner
      );
    }
    return BaseLayout(
      currentIndex: 1,
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
                                onChanged: _filterArticles,
                                decoration: InputDecoration(
                                  hintText: 'Search articles',
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
                          IconButton(
                            icon: const Icon(Icons.bookmark),
                            color: Color(0xFF90A955),
                            iconSize: 40,
                            onPressed: () {
                              final savedArticles = articlesDB
                                  .where((article) => user.savedArticles.contains(article.id))
                                  .toList();
                              Navigator.push(
                                context,
                                MaterialPageRoute(
                                  builder: (context) => SavedArticlesPage(
                                    savedArticles: savedArticles,
                                    onSaveToggle: (articleId) {
                                    setState(() {
                                      // Toggle saved status
                                      final articleIndex = articlesDB.indexWhere((article) => article.id == articleId);
                                      if (articleIndex != -1) {
                                        user.savedArticles.removeAt(articleIndex);
                                        
                                      }
                                    });
                                  },
                                  ),
                                ),
                              );
                            },
                          ),
                        ],
                      ),
                    ),
                  ],
                ),
              ),
              // Articles List
              Expanded(
                child: ListView.builder(
                  padding: const EdgeInsets.all(16),
                  itemCount: filteredArticles.length,
                  itemBuilder: (context, index) {
                    final article = filteredArticles[index];
                    return ArticleCard(
                      title: article.title,
                      description: article.content[0].text,
                      imageUrl: article.imageUrl,
                      saved: user.savedArticles.contains(article.id),
                      onTap: () {
                        Navigator.push(
                          context,
                          MaterialPageRoute(
                            builder: (context) => ArticleDetailPage(
                              title: article.title,
                              imageUrl: article.imageUrl,
                              content: article.content.map((content) => {
                                'title': content.title,
                                'text': content.text,
                              }).toList(),
                              comments: article.comments.map((comment) => {
                                'user': comment.user,
                                'text': comment.text,
                              }).toList(),
                              saved: user.savedArticles.contains(article.id),
                              onSaveToggle: () {
                                setState(() {
                                  // Toggle saved status
                                  if (index != -1) {
                                    if (user.savedArticles.contains(article.id)) {
                                      user.savedArticles.remove(article.id);
                                    } else {
                                      user.savedArticles.add(article.id);
                                    }
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
                            if (user.savedArticles.contains(article.id)) {
                              user.savedArticles.remove(article.id);
                            } else {
                              user.savedArticles.add(article.id);
                            }
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



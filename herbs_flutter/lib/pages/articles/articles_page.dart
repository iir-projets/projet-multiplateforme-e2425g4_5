/// articles page

import 'package:flutter/material.dart';
import 'package:herbs_flutter/pages/articles/article_card.dart';
import 'package:herbs_flutter/pages/articles/article_details.dart';
import 'package:herbs_flutter/pages/articles/article_saved.dart';
import 'package:herbs_flutter/pages/base_layout.dart';

class ArticlesPage extends StatefulWidget {
  const ArticlesPage({super.key});

  @override
  State<ArticlesPage> createState() => _ArticlesPageState();
}

class _ArticlesPageState extends State<ArticlesPage> {
  final TextEditingController _searchController = TextEditingController();
  List<Map<String, dynamic>> filteredArticles = _ArticlesPageState.articles;

  static List<Map<String, dynamic>> articles = [
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
  ];


  @override
  void initState() {
    super.initState();
    // Initialize with all articles
    filteredArticles = _ArticlesPageState.articles;
  }

  void _filterArticles(String query) {
    setState(() {
      filteredArticles = _ArticlesPageState.articles.where((article) {
        final title = article['title'].toLowerCase();
        final description = article['content'][0]['text'].toLowerCase();
        return title.contains(query.toLowerCase()) ||
            description.contains(query.toLowerCase());
      }).toList();
    });
  }
  

  @override
  Widget build(BuildContext context) {
    return BaseLayout(
      currentIndex: 1,
      child: Column(
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
                          final savedArticles = _ArticlesPageState.articles
                              .where((article) => article['saved'] == true)
                              .toList();
                          Navigator.push(
                            context,
                            MaterialPageRoute(
                              builder: (context) => SavedArticlesPage(
                                savedArticles: savedArticles,
                                onSaveToggle: (articleId) {
                                setState(() {
                                  // Toggle saved status
                                  final articleIndex = articles.indexWhere((article) => article['id'] == articleId);
                                  if (articleIndex != -1) {
                                    articles[articleIndex]['saved'] = !articles[articleIndex]['saved'];
                                    // Also update the filteredArticles if necessary
                                    final filteredIndex = filteredArticles.indexWhere((article) => article['id'] == articleId);
                                    if (filteredIndex != -1) {
                                      filteredArticles[filteredIndex]['saved'] = articles[articleIndex]['saved'];
                                    }
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
                  title: article['title'],
                  description: article['content'][0]['text'],
                  imageUrl: article['imageUrl'],
                  saved: article['saved'],
                  onTap: () {
                    Navigator.push(
                      context,
                      MaterialPageRoute(
                        builder: (context) => ArticleDetailPage(
                          title: article['title'],
                          imageUrl: article['imageUrl'],
                          content: article['content'],
                          comments: article['comments'],
                          saved: article['saved'],
                          onSaveToggle: () {
                            setState(() {
                              // Toggle saved status
                              if (index != -1) {
                                articles[index]['saved'] = !articles[index]['saved'];
                                // Also update the filteredArticles if necessary
                                final filteredIndex = filteredArticles.indexWhere((article) => article['id'] == articles[index]['id']);
                                if (filteredIndex != -1) {
                                  filteredArticles[filteredIndex]['saved'] = articles[index]['saved'];
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
                        articles[index]['saved'] = !articles[index]['saved'];
                        // Also update the filteredArticles if necessary
                        final filteredIndex = filteredArticles.indexWhere((article) => article['id'] == articles[index]['id']);
                        if (filteredIndex != -1) {
                          filteredArticles[filteredIndex]['saved'] = articles[index]['saved'];
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
    );
  }
}



import 'package:flutter/material.dart';
import 'package:herbs_flutter/data/articles.dart';
import 'package:herbs_flutter/data/users.dart';
import 'package:herbs_flutter/pages/articles/article_card.dart';
import 'package:herbs_flutter/pages/articles/article_details.dart';
import 'package:herbs_flutter/services/user_service.dart';

class SavedArticlesPage extends StatefulWidget {
  final List<Articles> savedArticles;
  final Function(String) onSaveToggle;

  const SavedArticlesPage({
    super.key, required this.savedArticles, 
    required this.onSaveToggle});

  @override
  State<SavedArticlesPage> createState() => _SavedArticlesPageState();
}

class _SavedArticlesPageState extends State<SavedArticlesPage> {
  late List<Articles> _savedArticles;
  late Users user;
  bool _isUserInitialized = false;
  @override
  void initState() {
    super.initState();
    _initializeUser();
    _savedArticles = List.from(widget.savedArticles); // Create a local mutable copy
  }
  void _initializeUser() async{
    final UserService user_service = UserService();

    final userData = await user_service.fetchUserData();
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
}

  void _removeArticle(int index) {
    setState(() {
      _savedArticles.removeAt(index); // Remove the article from the list
    });
  }

  @override
  Widget build(BuildContext context) {
    if (!_isUserInitialized) {
      return const Center(
        child: CircularProgressIndicator(), // Show loading spinner
      );
    }
    return Scaffold(
      appBar: AppBar(
        backgroundColor: const Color(0xFF90A955),
        leading: IconButton(
          icon: const Icon(Icons.arrow_back),
          onPressed: () => Navigator.pop(context),
        ),
        title: const Text('Saved Articles'),
      ),
      body: _savedArticles.isEmpty
          ? Center(
              child: Text(
                'No saved articles yet!',
                style: Theme.of(context).textTheme.titleLarge,
              ),
            )
          : ListView.builder(
              padding: const EdgeInsets.all(16),
              itemCount: _savedArticles.length,
              itemBuilder: (context, index) {
                final article = _savedArticles[index];
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
                          content: article.content.map((c) => {
                            'title': c.title,
                            'text': c.text}).toList(),
                          comments: article.comments.map((c) => {
                            'user': c.user,
                            'text': c.text}).toList(),
                          saved: user.savedArticles.contains(article.id),
                          onSaveToggle: () {
                            setState(() {
                              // Toggle saved status
                              widget.onSaveToggle(article.id);
                              _removeArticle(index);
                            });
                          },
                        ),
                      ),
                    );
                  },
                  onSaveToggle: () => _removeArticle(index), // Remove when unsaved
                );
              },
            ),
    );
  }
}

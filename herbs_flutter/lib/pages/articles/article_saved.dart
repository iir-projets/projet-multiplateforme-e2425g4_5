import 'package:flutter/material.dart';
import 'package:herbs_flutter/pages/articles/article_card.dart';
import 'package:herbs_flutter/pages/articles/article_details.dart';

class SavedArticlesPage extends StatefulWidget {
  final List<Map<String, dynamic>> savedArticles;
  final Function(int) onSaveToggle;

  const SavedArticlesPage({
    super.key, required this.savedArticles, 
    required this.onSaveToggle});

  @override
  State<SavedArticlesPage> createState() => _SavedArticlesPageState();
}

class _SavedArticlesPageState extends State<SavedArticlesPage> {
  late List<Map<String, dynamic>> _savedArticles;

  @override
  void initState() {
    super.initState();
    _savedArticles = List.from(widget.savedArticles); // Create a local mutable copy
  }

  void _removeArticle(int index) {
    setState(() {
      _savedArticles[index]['saved'] = false; // Update saved status
      _savedArticles.removeAt(index); // Remove the article from the list
    });
  }

  @override
  Widget build(BuildContext context) {
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
                              widget.onSaveToggle(article['id']);
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

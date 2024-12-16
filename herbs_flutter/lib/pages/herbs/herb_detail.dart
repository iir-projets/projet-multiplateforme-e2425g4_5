import 'package:flutter/material.dart';

class HerbDetailPage extends StatefulWidget {
  final String title;
  final String imageUrl;
  final bool saved;
  final List<Map<String, String>> content;
  final List<Map<String, String>> comments;
  final VoidCallback onSaveToggle;

  const HerbDetailPage({
    super.key,
    required this.title,
    required this.imageUrl,
    required this.content,
    required this.comments,
    required this.saved,
    required this.onSaveToggle,
  });

  @override
  _HerbDetailPageState createState() => _HerbDetailPageState();
}

class _HerbDetailPageState extends State<HerbDetailPage>{

  final TextEditingController _commentController = TextEditingController();
  late bool isSaved;
  @override
  void initState() {
    super.initState();
    isSaved = widget.saved;
  }
  

  void _addComment() {
    if (_commentController.text.isNotEmpty) {
      setState(() {
        
        widget.comments.add({
          'user': 'You', // You can replace this with the actual user name
          'text': _commentController.text,
        });
        _commentController.clear(); // Clear the input field
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: CustomScrollView(
        slivers: [
          // App Bar
          SliverAppBar(
            expandedHeight: 200,
            backgroundColor: const Color(0xFF90A955),
            pinned: true,
            flexibleSpace: FlexibleSpaceBar(
              title: Text(
                widget.title,
                style: const TextStyle(
                  color: Colors.white,
                  fontSize: 16,
                ),
              ),
              background: Image.asset(
                widget.imageUrl,
                fit: BoxFit.cover,
              ),
            ),
            leading: IconButton(
              icon: const Icon(Icons.arrow_back),
              onPressed: () => Navigator.pop(context),
            ),
            actions: [
              IconButton(
              icon: Icon(
                widget.saved ? Icons.favorite_rounded : Icons.favorite_border_rounded, // Dynamic icon
                color: widget.saved ? Color.fromARGB(255, 220, 53, 70) : Color.fromARGB(255, 220, 53, 70), // Change color as needed
              ),
              iconSize: 35,
              onPressed: (){
                
                setState(() {
                  widget.onSaveToggle();
                  isSaved = !widget.saved; // Toggle saved state
                });
                
                }, // Toggle callback
            ),
            ],
          ),
          // Herb Content
          SliverToBoxAdapter(
            child: Padding(
              padding: const EdgeInsets.all(16.0),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  for (var section in widget.content)
                    Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          section['title'] ?? '',
                          style: const TextStyle(
                            fontSize: 20,
                            fontWeight: FontWeight.bold,
                          ),
                        ),
                        const SizedBox(height: 8),
                        Text(
                          section['text'] ?? '',
                          style: const TextStyle(fontSize: 16),
                        ),
                        const SizedBox(height: 16),
                      ],
                    ),
                ],
              ),
            ),
          ),
          // Comments Section
          SliverToBoxAdapter(
            child: Padding(
              padding: const EdgeInsets.all(16.0),
              child: Container(
                decoration: BoxDecoration(
                  color: const Color.fromARGB(255, 254, 238, 243),
                  borderRadius: BorderRadius.circular(12),
                  boxShadow: [
                    BoxShadow(
                      color: const Color(0xFFE8F5E9),
                      spreadRadius: 2,
                      blurRadius: 5,
                      offset: const Offset(0, 3),
                    ),
                  ],
                ),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    const Padding(
                      padding: EdgeInsets.all(10.0),
                      child: Text(
                        'Comments',
                        style: TextStyle(
                          fontSize: 20,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                    ),
                    SizedBox(
                      height: 300, // Fixed height for the comments box
                      child: ListView.builder(
                        padding: const EdgeInsets.all(16),
                        itemCount: widget.comments.length, // Use actual comments count
                        itemBuilder: (context, index) {
                          final comment = widget.comments[index];
                          return Container(
                            margin: const EdgeInsets.only(bottom: 16),
                            padding: const EdgeInsets.all(12),
                            decoration: BoxDecoration(
                              color: const Color(0xFFF5F5F5),
                              borderRadius: BorderRadius.circular(8),
                              boxShadow: [
                                BoxShadow(
                                  color: Colors.grey.withOpacity(0.3),
                                  spreadRadius: 1,
                                  blurRadius: 3,
                                  offset: const Offset(0, 1),
                                ),
                              ],
                            ),
                            child: Column(
                              crossAxisAlignment: CrossAxisAlignment.start,
                              children: [
                                Text(
                                  comment['user'] ?? '',
                                  style: const TextStyle(
                                    fontWeight: FontWeight.bold,
                                  ),
                                ),
                                const SizedBox(height: 4),
                                Text(
                                  comment['text'] ?? '',
                                  style: const TextStyle(fontSize: 14),
                                ),
                              ],
                            ),
                          );
                        },
                      ),
                    ),
                    // Add Comment Input
                    Padding(
                      padding: const EdgeInsets.all(16.0),
                      child: Row(
                        children: [
                          Expanded(
                            child: TextField(
                              controller: _commentController,
                              decoration: InputDecoration(
                                hintText: 'Add a comment...',
                                border: OutlineInputBorder(
                                  borderRadius: BorderRadius.circular(25.0),
                                  borderSide: BorderSide.none,
                                ),
                                filled: true,
                                fillColor: Colors.white,
                                contentPadding: const EdgeInsets.symmetric(
                                  horizontal: 16,
                                  vertical: 8,
                                ),
                              ),
                            ),
                          ),
                          const SizedBox(width: 8),
                          IconButton(
                            icon: const Icon(Icons.send),
                            onPressed: _addComment,
                            color: const Color(0xFF90A955),
                          ),
                        ],
                      ),
                    ),
                  ],
                ),
              ),
            ),
          ),
        ],
      ),
    );
  }
  

}
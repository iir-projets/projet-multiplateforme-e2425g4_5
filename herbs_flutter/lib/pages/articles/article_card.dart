import 'package:flutter/material.dart';

class ArticleCard extends StatefulWidget {
  final String title;
  final String description;
  final String imageUrl;
  final bool saved; // Add saved status
  final VoidCallback onTap;
  final VoidCallback onSaveToggle; // Callback for toggling save status

  const ArticleCard({
    super.key,
    required this.title,
    required this.description,
    required this.imageUrl,
    required this.saved,
    required this.onTap,
    required this.onSaveToggle,
  });

  @override
  State<ArticleCard> createState() => _ArticleCardState();
}

class _ArticleCardState extends State<ArticleCard> {
  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: widget.onTap,
      child: Container(
        margin: const EdgeInsets.only(bottom: 16),
        decoration: BoxDecoration(
          color: const Color(0xFFF5F5F5),
          borderRadius: BorderRadius.circular(15),
        ),
        child: Row(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            // Article Image
            ClipRRect(
              borderRadius: const BorderRadius.only(
                topLeft: Radius.circular(15),
                bottomLeft: Radius.circular(15),
              ),
              child: Image.network(
                widget.imageUrl,
                width: 100,
                height: 120,
                fit: BoxFit.cover,
              ),
            ),
            // Article Content
            Expanded(
              child: Padding(
                padding: const EdgeInsets.all(10),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                      widget.title,
                      style: const TextStyle(
                        fontSize: 18,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                    const SizedBox(height: 4),
                    Text(
                      (widget.description).replaceAll('\\n', '\n'),
                      maxLines: 2,
                      overflow: TextOverflow.ellipsis,
                      style: const TextStyle(
                        fontSize: 14,
                        color: Colors.grey,
                      ),
                    ),
                  ],
                ),
              ),
            ),
            // Bookmark Icon
            IconButton(
              icon: Icon(
                widget.saved ? Icons.bookmark : Icons.bookmark_border, // Dynamic icon
                color: widget.saved ? Color(0xFF90A955) : Color(0xFF90A955), // Highlight if saved
              ),
              iconSize: 25,
              onPressed: widget.onSaveToggle, // Toggle callback
            ),
          ],
        ),
      ),
    );
  }
}

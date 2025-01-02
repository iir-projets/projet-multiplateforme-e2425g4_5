import 'package:flutter/material.dart';

class HerbCard extends StatefulWidget {
  final String name;
  final String imageUrl;
  final bool saved; // Add saved status
  final VoidCallback onTap;
  final VoidCallback onSaveToggle; // Callback for toggling save status

  const HerbCard({
    super.key,
    required this.name,
    required this.imageUrl,
    required this.saved,
    required this.onTap,
    required this.onSaveToggle,
  });

  @override
  State<HerbCard> createState() => _HerbCardState();
}

class _HerbCardState extends State<HerbCard> {
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
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          
          children: [
            // Stack for Herb Image and Bookmark Icon
            Stack(
              children: [
                // Herb Image
                ClipRRect(
                  borderRadius: const BorderRadius.only(
                    topLeft: Radius.circular(15),
                    topRight: Radius.circular(15),
                  ),
                  child: Image.network(
                    widget.imageUrl,
                    width: double.infinity, // Make the image take full width
                    height: 210,
                    fit: BoxFit.cover,
                  ),
                ),
                // Bookmark Icon
                Positioned(
                  top: 10, // Adjust the position as needed
                  right: 10, // Adjust the position as needed
                  child: IconButton(
                    icon: Icon(
                      widget.saved ? Icons.favorite_rounded : Icons.favorite_border_rounded, // Dynamic icon
                      color: widget.saved ? Color.fromARGB(255, 220, 53, 70) : Color.fromARGB(255, 220, 53, 70), // Change color as needed
                    ),
                    iconSize: 35,
                    onPressed: widget.onSaveToggle, // Toggle callback
                  ),
                ),
              ],
            ),
            // Herb Content
            Padding(
              padding: const EdgeInsets.all(8.0),
              
              child: Text(
                widget.name ?? '',
                style: const TextStyle(
                  fontSize: 18,
                  fontWeight: FontWeight.bold,
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
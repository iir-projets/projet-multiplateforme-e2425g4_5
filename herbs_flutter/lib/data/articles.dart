class Articles {
  final String id;
  final String title;
  final String imageUrl;
  final List<Content> content;
  final List<Comments> comments;
  Articles({
    required this.id,
    required this.title,
    required this.imageUrl,
    required this.content,
    required this.comments
  });
}

class Content{
  final String title;
  final String text;
  Content({
    required this.title,
    required this.text
  });
}

class Comments{
  final String user;
  final String text;
  Comments({
    required this.user,
    required this.text
  });
}
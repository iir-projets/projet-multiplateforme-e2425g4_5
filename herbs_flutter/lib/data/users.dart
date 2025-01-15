class Users {
  final String id;
  final String email;
  final String firstName;
  final String lastName;
  final String mobile;
  final String address;
  final String password;
  final List<dynamic> savedArticles;
  final List<dynamic> favoredHerbs;
  final List<dynamic> deseases;
  final List<dynamic> allergies;
  final List<dynamic> medicines;
  final List<Notification> notifications;
  
  
  Users({
    required this.id,
    required this.email,
    required this.firstName,
    required this.lastName,
    required this.mobile,
    required this.address,
    required this.password,
    required this.savedArticles,
    required this.favoredHerbs,
    required this.deseases,
    required this.allergies,
    required this.medicines,
    required this.notifications,
  });
}

class Notification {
  final int id;
  final String title;
  final String description;
  final String articleId;
  final String imageUrl;
  bool isRead;
  
  Notification({
    required this.id,
    required this.title,
    required this.description,
    required this.articleId,
    required this.imageUrl,
    required this.isRead,
  });

  void markAsRead(){
    // Mark notification as read
    this.isRead = true;
  }

}
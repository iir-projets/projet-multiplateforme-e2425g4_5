import 'package:flutter/material.dart';
import 'package:herbs_flutter/data/articles.dart';
import 'package:herbs_flutter/data/users.dart' as user_data;
import 'package:herbs_flutter/pages/articles/article_details.dart';
import 'package:herbs_flutter/pages/base_layout.dart';
import 'package:herbs_flutter/pages/notifications/notification_card.dart';
import 'package:herbs_flutter/services/user_service.dart';


// Constant table for notifications


class NotificationsPage extends StatefulWidget {
  const NotificationsPage({super.key});

  @override
  State<NotificationsPage> createState() => _NotificationsPageState();
}

class _NotificationsPageState extends State<NotificationsPage> {

  // User data
  final UserService user_service = UserService();
  late user_data.Users user;
  bool _isUserInitialized = false;

  List<user_data.Notification> notifications = [];

  @override
  void initState() {
    super.initState();
    _initializeUser();
    
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
          address: userData.data()['adress']??'',
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

        notifications = user.notifications;
        _isUserInitialized = true;
      });// Initialize user data

    }catch(e){
      print('Error fetching user: $e');
    }
  }


  void _markAsRead(user_data.Notification notification) async {
    
    Articles article = await user_service.getArticleById(notification.articleId, user) as Articles;
    setState(()  {
      notification.markAsRead();
      user_service.UpdateNotifications(notification.id, user);
      // Navigate to article
      Navigator.push(
        context,
        MaterialPageRoute(
          builder: (context) => ArticleDetailPage(
            user: user,
            articleId: article.id,
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
                
                  if (user.savedArticles.contains(article.id)) {
                    user_service.RemoveSavedArticle(article.id, user);
                    //user.savedArticles.remove(article.id);
                  } else {
                    user_service.AddSavedArticle(article, user);
                    user.savedArticles.add(article.id);
                  }
                  _initializeUser();
                
              });
            },

            

          ),
        ),
      );
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
      currentIndex: 5,
      child: Column(
        children: [
          // Banner
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
                // 
                Padding(
                  padding: const EdgeInsets.all(18.0),
                  child: Row(
                    children: [
                      
                      const SizedBox(width: 8),
                    ],
                  ),
                ),
              ],
            ),
          ),
          // Notifications List
          Expanded(
            child: ListView.builder(
              padding: const EdgeInsets.all(16),
              itemCount: notifications.length,
              itemBuilder: (context, index) {
                final notification = notifications[index];
                return NotificationCard(
                  notification: notification,
                  onTap: () {
                    _markAsRead(notification);
                    
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


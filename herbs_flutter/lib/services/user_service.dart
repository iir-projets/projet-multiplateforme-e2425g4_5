import 'package:firebase_auth/firebase_auth.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:herbs_flutter/data/articles.dart';
import 'package:herbs_flutter/data/herbs.dart';
import 'package:herbs_flutter/data/users.dart';

class UserService {

  final FirebaseAuth _auth = FirebaseAuth.instance;
  final FirebaseFirestore _firestore = FirebaseFirestore.instance;
  // Fetch the authenticated user's data
  Future<QueryDocumentSnapshot<Map<String, dynamic>>> fetchUserData() async {
    final user = FirebaseAuth.instance.currentUser;

    if (user == null) {
      throw Exception("No user is logged in");
    }

    final userDoc = await FirebaseFirestore.instance
        .collection('users')
        .where('email', isEqualTo: user.email)
        .get();

    if (userDoc.docs.isEmpty) {
      throw Exception("User data not found in Firestore");
    }

    return userDoc.docs.first;
  }

  Future<void> registerUser({
    required String firstName,
    required String lastName,
    required String mobile,
    required String email,
    required String password,
  }) async {
    if (firstName.isEmpty || lastName.isEmpty || mobile.isEmpty || email.isEmpty || password.isEmpty) {
      throw Exception('Fields cannot be empty');
    }
    try {
      // Register the user with Firebase Authentication
      final UserCredential userCredential = await _auth.createUserWithEmailAndPassword(
        email: email,
        password: password,
      );

      final user = userCredential.user;

      if (user != null) {
        // Store additional user details in Firestore
        try{
          await _firestore.collection('users').add({
            'firstName': firstName,
            'lastName': lastName,
            'mobile': mobile,
            'email': email,
            'savedArticles': [],
            'favoredHerbs': [],
            'deseases': [],
            'allergies': [],
            'medicines': []
          });
        }
        catch(e){
          throw Exception('Registration failed: ${e.toString()}');
        }
        

      }
    } catch (e) {
      throw Exception('Registration failed: ${e.toString()}');
    }
  }

//////////     HERBS    //////////
  Future<void> RemoveFavoriteHerb(Herbs herb, Users user) async {
    try {
      await _firestore.collection('users').doc(user.id).update({
        'favoredHerbs': FieldValue.arrayRemove([herb.id])
      });
    } catch (e) {
      throw Exception('Failed to remove herb from favorites: ${e.toString()}');
    }
  }

  Future<void> AddFavoriteHerb(Herbs herb, Users user) async {
    try {
      await _firestore.collection('users').doc(user.id).update({
        'favoredHerbs': FieldValue.arrayUnion([herb.id])
      });
    } catch (e) {
      throw Exception('Failed to add herb to favorites: ${e.toString()}');
    }
  }

//////////     ARTICLES    //////////

  Future<Articles?> getArticleById(String articleId, Users user){
    return _firestore.collection('articles').doc(articleId).get().then((doc) {
      if (doc.exists) {
        Articles article = Articles(
          id: doc.id, 
          title: doc.data()?['title'], 
          imageUrl: doc.data()?['imageUrl'],
          content: (doc.data()?['content'] as List<dynamic>)
            .map((e) => Content(title: e['title'], text: e['text']))
            .toList(),
          comments: (doc.data()?['comments'] as List<dynamic>)
            .map((e) => Comments(user: e['user'], text: e['text']))
            .toList(),
          
          );

        return article;
      } else {
        return null;
      }
      }).catchError((error) {
        throw Exception('Failed to get article by id: ${error.toString()}');
      });
  }

  Future<void> RemoveSavedArticle(String articleId, Users user) async {
    try {
      await _firestore.collection('users').doc(user.id).update({
        'savedArticles': FieldValue.arrayRemove([articleId])
      });
    } catch (e) {
      throw Exception('Failed to remove article from saved: ${e.toString()}');
    }
  }

  Future<void> AddSavedArticle(Articles article, Users user) async {
    try {
      await _firestore.collection('users').doc(user.id).update({
        'savedArticles': FieldValue.arrayUnion([article.id])
      });
    } catch (e) {
      throw Exception('Failed to add article to saved: ${e.toString()}');
    }
  }

  Future<void> AddComment(String articleId, String comment, Users user) async {
    try {
      await _firestore.collection('articles').doc(articleId).update({
        'comments': FieldValue.arrayUnion([{
          'user': '${user.firstName} ${user.lastName}',
          'text': comment
        }])
      });
    } catch (e) {
      throw Exception('Failed to add comment: ${e.toString()}');
    }
  }

  //////////     DESEASES    //////////

  Future<void> AddDesease(String desease, Users user) async {
    try {
      await _firestore.collection('users').doc(user.id).update({
        'deseases': FieldValue.arrayUnion([desease])
      });
    } catch (e) {
      throw Exception('Failed to add desease: ${e.toString()}');
    }
  }

  Future<void> RemoveDesease(String desease, Users user) async {
    try {
      await _firestore.collection('users').doc(user.id).update({
        'deseases': FieldValue.arrayRemove([desease])
      });
    } catch (e) {
      throw Exception('Failed to remove desease: ${e.toString()}');
    }
  }

  //////////     ALLERGIES    //////////
  Future<void> AddAllergy(String allergy, Users user) async {
    try {
      await _firestore.collection('users').doc(user.id).update({
        'allergies': FieldValue.arrayUnion([allergy])
      });
    } catch (e) {
      throw Exception('Failed to add allergy: ${e.toString()}');
    }
  }

  Future<void> RemoveAllergy(String allergy, Users user) async {
    try {
      await _firestore.collection('users').doc(user.id).update({
        'allergies': FieldValue.arrayRemove([allergy])
      });
    } catch (e) {
      throw Exception('Failed to remove allergy: ${e.toString()}');
    }
  }

  //////////     MEDICINES    //////////
  Future<void> AddMedicine(String medicine, Users user) async {
    try {
      await _firestore.collection('users').doc(user.id).update({
        'medicines': FieldValue.arrayUnion([medicine])
      });
    } catch (e) {
      throw Exception('Failed to add medicine: ${e.toString()}');
    }
  }

  Future<void> RemoveMedicine(String medicine, Users user) async {
    try {
      await _firestore.collection('users').doc(user.id).update({
        'medicines': FieldValue.arrayRemove([medicine])
      });
    } catch (e) {
      throw Exception('Failed to remove medicine: ${e.toString()}');
    }
  }

  //////////    FIRST NAME    //////////
  Future<void> UpdateFirstName(String firstName, Users user) async {
    try {
      await _firestore.collection('users').doc(user.id).update({
        'firstName': firstName
      });
    } catch (e) {
      throw Exception('Failed to update first name: ${e.toString()}');
    }
  }

  //////////    LAST NAME    //////////
  Future<void> UpdateLastName(String lastName, Users user) async {
    try {
      await _firestore.collection('users').doc(user.id).update({
        'lastName': lastName
      });
    } catch (e) {
      throw Exception('Failed to update last name: ${e.toString()}');
    }
  }

  //////////    MOBILE    //////////
  Future<void> UpdateMobile(String mobile, Users user) async {
    try {
      await _firestore.collection('users').doc(user.id).update({
        'mobile': mobile
      });
    } catch (e) {
      throw Exception('Failed to update mobile: ${e.toString()}');
    }
  }

  //////////   ADRESS   //////////
  Future<void> UpdateAdress(String adress, Users user) async {
    try {
      await _firestore.collection('users').doc(user.id).update({
        'adress': adress
      });

    } catch (e) {
      throw Exception('Failed to update adress: ${e.toString()}');
    }
  }


  //////////    PASSWORD    //////////
  Future<void> UpdatePassword(String password, Users user) async {
    try {
      await _auth.currentUser!.updatePassword(password);
    } catch (e) {
      throw Exception('Failed to update password: ${e.toString()}');
    }
  }

  //////////   NOTIFICATIONS   //////////
  Future<void> UpdateNotifications(int notificationId, Users user) async {
  try {
    // Find the notification by its id
    List<Notification> updatedNotifications = user.notifications.map((notification) {
      if (notification.id == notificationId) {
        return Notification(
          id: notification.id,
          title: notification.title,
          description: notification.description,
          articleId: notification.articleId,
          imageUrl: notification.imageUrl,
          isRead: true, // Update isRead to true
        );
      }
      return notification;
    }).toList();

    // Update the user's document in Firestore
    await _firestore.collection('users').doc(user.id).update({
      'notifications': updatedNotifications.map((notification) => {
        'id': notification.id,
        'title': notification.title,
        'description': notification.description,
        'articleId': notification.articleId,
        'imageUrl': notification.imageUrl,
        'isRead': notification.isRead,
      }).toList(),
    });
  } catch (e) {
    throw Exception('Failed to update notifications: ${e.toString()}');
  }
}


  //////////   END OF USER SERVICE   //////////
  Future<void> Logout() async {
    try {
      await _auth.signOut();
    } catch (e) {
      throw Exception('Failed to logout: ${e.toString()}');
    }
  }
  


}

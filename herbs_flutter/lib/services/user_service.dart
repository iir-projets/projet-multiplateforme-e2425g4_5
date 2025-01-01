import 'package:firebase_auth/firebase_auth.dart';
import 'package:cloud_firestore/cloud_firestore.dart';

class UserService {
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
}

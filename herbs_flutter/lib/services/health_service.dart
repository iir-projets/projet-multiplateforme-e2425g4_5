
import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:herbs_flutter/data/health.dart';

class HealthService {
  final FirebaseFirestore _firestore = FirebaseFirestore.instance;

  Future<List<dynamic>> fetchDeseases() async {
    final deseaseDoc = await _firestore.collection('deseases').get();
    final desease = deseaseDoc.docs.map((doc) => Deseases(desease: doc.data()['desease'])).toList();
    List<String> deseaseList = [];
    for (var i = 0; i < desease.length; i++) {
      deseaseList.add(desease[i].desease);
    }
    return deseaseList;
  }

  Future<List<dynamic>> fetchAllergies() async {
    final allergyDoc = await _firestore.collection('allergies').get();
    final allergy = allergyDoc.docs.map((doc) => Allergies(allergy: doc.data()['allergy'])).toList();
    List<String> allergyList = [];
    for (var i = 0; i < allergy.length; i++) {
      allergyList.add(allergy[i].allergy);
    }
    return allergyList;
  }

  Future<List<dynamic>> fetchMedicines() async {
    final medicineDoc = await _firestore.collection('medicines').get();
    final medicine = medicineDoc.docs.map((doc) => Medicines(medicine: doc.data()['medicine'])).toList();

    List<String> medicineList = [];
    for (var i = 0; i < medicine.length; i++) {
      medicineList.add(medicine[i].medicine);
    }
    return medicineList;
  }
}
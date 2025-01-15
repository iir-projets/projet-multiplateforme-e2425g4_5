import 'package:flutter/material.dart';
import 'package:herbs_flutter/data/users.dart' as user_data;
import 'package:herbs_flutter/pages/base_layout.dart';
import 'package:herbs_flutter/services/health_service.dart';
import 'package:herbs_flutter/services/user_service.dart';
import 'package:image_picker/image_picker.dart';
import 'dart:io';
import 'package:flutter/foundation.dart' show kIsWeb;

class ProfilePage extends StatefulWidget {
  const ProfilePage({Key? key}) : super(key: key);

  @override
  _ProfilePageState createState() => _ProfilePageState();
}

class _ProfilePageState extends State<ProfilePage> {

  // User data
  final UserService user_service = UserService();
  final HealthService health_service = HealthService();
  late user_data.Users user;
  bool _isUserInitialized = false;
  // Sample data for dropdowns
  final List<dynamic> allergies = [];
  final List<dynamic> medicines = [];
  final List<dynamic> diseases = [];

  // Selected items
  List<dynamic> selectedAllergies = [];
  List<dynamic> selectedMedicines = [];
  List<dynamic> selectedDiseases = [];

  // Controllers for editing fields
  late TextEditingController firstNameController;
  late TextEditingController lastNameController;
  late TextEditingController mobileController;
  late TextEditingController addressController;
  late TextEditingController newPasswordController;
  late TextEditingController confirmPasswordController;

  // Profile image
  dynamic _image;
  final ImagePicker _picker = ImagePicker();

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
        _isUserInitialized = true;
      });// Initialize user data

      // Set the initial values for the controllers
      firstNameController = TextEditingController(text: user.firstName);
      lastNameController = TextEditingController(text: user.lastName);
      mobileController = TextEditingController(text: user.mobile);
      addressController = TextEditingController(text: user.address);
      selectedAllergies.addAll(user.allergies);
      selectedMedicines.addAll(user.medicines);
      selectedDiseases.addAll(user.deseases);
      newPasswordController = TextEditingController();
      confirmPasswordController = TextEditingController();
    }catch(e){
      print('Error fetching user: $e');
    }
  }


  void _initializeHealth() async{
    final allergiesData = await health_service.fetchAllergies();
    final medicinesData = await health_service.fetchMedicines();
    final diseasesData = await health_service.fetchDeseases();

    setState(() {
      allergies.addAll(allergiesData);
      medicines.addAll(medicinesData);
      diseases.addAll(diseasesData);
    });
  }

  @override
  void initState() {
    super.initState();
    _initializeUser();
    _initializeHealth();
    
  }

  void _updateUserHealth(String label,dynamic item, bool selected) async{

    if (label == 'allergy') {
        if (selected) {
          try{
            await user_service.AddAllergy(item.toString(), user);
          }catch(e){
            print('Error adding allergy: $e');
          }
        //selectedAllergies.add(item);
      } else {
          try{
            await user_service.RemoveAllergy(item.toString(), user);
          }catch(e){
            print('Error removing allergy: $e');
          }
        //selectedAllergies.remove(item);
      }
    }
    if (label == 'medicine') {
      if (selected) {
        try{
          await user_service.AddMedicine(item.toString(), user);
        }catch(e){
          print('Error adding medicine: $e');
        }
        //selectedMedicines.add(item);
      } else {
        try{
          await user_service.RemoveMedicine(item.toString(), user);
        }catch(e){
          print('Error removing medicine: $e');
        }
        //selectedMedicines.remove(item);
      }
    }
    if (label == 'disease')
    {
      if (selected) {
        try{
          await user_service.AddDesease(item.toString(), user);
        }catch(e){
          print('Error adding desease: $e');
        }
        //selectedDiseases.add(item);
      } else {
        try{
          await user_service.RemoveDesease(item.toString(), user);
        }catch(e){
          print('Error removing desease: $e');
        }
        //selectedDiseases.remove(item);
      }
    }
    _initializeUser();
  }

  @override
  void dispose() {
    firstNameController.dispose();
    lastNameController.dispose();
    mobileController.dispose();
    addressController.dispose();
    newPasswordController.dispose();
    confirmPasswordController.dispose();
    super.dispose();
  }

  Future<void> _getImage() async {
    try {
      final XFile? pickedFile = await _picker.pickImage(source: ImageSource.gallery);

      if (pickedFile != null) {
        setState(() {
          if (kIsWeb) {
            // For web, we'll use the path as a network image
            _image = NetworkImage(pickedFile.path);
          } else {
            // For mobile, we'll use a file
            _image = File(pickedFile.path);
          }
        });
      }
    } catch (e) {
      print('Error picking image: $e');
      // Show an error message to the user
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text('Unable to pick image. Please try again.')),
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    if (!_isUserInitialized) {
      return const Center(
        child: CircularProgressIndicator(), // Show loading spinner
      );
    }
    return BaseLayout(
      currentIndex: 4,
      child: Scaffold(
        body: Container(
          color: const Color(0xFFF5F5F5),
          child: SingleChildScrollView(
            child: Padding(
              padding: const EdgeInsets.all(16.0),
              child: Column(
                children: [
                  const SizedBox(height: 48),
                  // Profile Icon
                  Stack(
                    children: [
                      Container(
                        width: 120,
                        height: 120,
                        decoration: BoxDecoration(
                          color: const Color(0xFF90A955),
                          shape: BoxShape.circle,
                          image: _image != null
                              ? DecorationImage(
                                  image: kIsWeb
                                      ? _image
                                      : FileImage(_image as File),
                                  fit: BoxFit.cover,
                                )
                              : null,
                        ),
                        child: _image == null
                            ? const Icon(
                                Icons.person,
                                size: 60,
                                color: Colors.white,
                              )
                            : null,
                      ),
                      Positioned(
                        bottom: 0,
                        right: 0,
                        child: GestureDetector(
                          onTap: _getImage,
                          child: Container(
                            padding: const EdgeInsets.all(8),
                            decoration: const BoxDecoration(
                              color: Color(0xFF90A955),
                              shape: BoxShape.circle,
                            ),
                            child: const Icon(
                              Icons.camera_alt,
                              color: Colors.white,
                              size: 20,
                            ),
                          ),
                        ),
                      ),
                    ],
                  ),
                  const SizedBox(height: 24),
                  
                  // Personal Information Section
                  Container(
                    padding: const EdgeInsets.all(16),
                    decoration: BoxDecoration(
                      color: const Color(0xFF90A955).withOpacity(0.2),
                      borderRadius: BorderRadius.circular(12),
                    ),
                    child: Column(
                      children: [
                        _buildEditableField('firstName', firstNameController),
                        _buildEditableField('lastName', lastNameController),
                        _buildEditableField('mobile', mobileController),
                        _buildEditableField('address', addressController),
                        _buildPasswordChangeField(),
                      ],
                    ),
                  ),
                  
                  const SizedBox(height: 16),
                  
                  // Health Information Section
                  Container(
                    padding: const EdgeInsets.all(16),
                    decoration: BoxDecoration(
                      color: const Color(0xFF90A955).withOpacity(0.2),
                      borderRadius: BorderRadius.circular(12),
                    ),
                    child: Column(
                      children: [
                        _buildMultiSelect('allergy', allergies, selectedAllergies),
                        _buildMultiSelect('medicine', medicines, selectedMedicines),
                        _buildMultiSelect('disease', diseases, selectedDiseases),
                      ],
                    ),
                  ),
                  
                  const SizedBox(height: 16),
                  
                  // Logout Button
                  Container(
                    padding: const EdgeInsets.all(16),
                    decoration: BoxDecoration(
                      color: const Color(0xFF90A955).withOpacity(0.2),
                      borderRadius: BorderRadius.circular(12),
                    ),
                    child: InkWell(
                      onTap: () {
                        // Handle logout
                        user_service.Logout();
                        Navigator.of(context).pushReplacementNamed('/login');
                      },
                      child: Row(
                        children: const [
                          Text(
                            'log out',
                            style: TextStyle(
                              fontSize: 16,
                              color: Colors.black87,
                            ),
                          ),
                          Spacer(),
                          Icon(Icons.logout, color: Colors.red),
                        ],
                      ),
                    ),
                  ),
                ],
              ),
            ),
          ),
        ),
      ),
    );
  }

  Widget _buildEditableField(String label, TextEditingController controller) {
    return Container(
      margin: const EdgeInsets.only(bottom: 12),
      decoration: BoxDecoration(
        color: Colors.white,
        borderRadius: BorderRadius.circular(8),
      ),
      child: ExpansionTile(
        title: Text(
          label,
          style: const TextStyle(
            fontSize: 16,
            color: Colors.black87,
          ),
        ),
        children: [
          Padding(
            padding: const EdgeInsets.all(16.0),
            child: Row(
              children: [
                Expanded(
                  child: TextField(
                    controller: controller,
                    decoration: InputDecoration(
                      hintText: 'Enter $label',
                      border: OutlineInputBorder(),
                    ),
                  ),
                ),
                IconButton(
                  icon: Icon(Icons.check, color: Color(0xFF90A955)),
                  onPressed: () {
                    // Validate and save the changes
                    setState(() {
                      if (label == 'firstName') {
                        try{
                        user_service.UpdateFirstName(controller.text, user) ;
                        ScaffoldMessenger.of(context).showSnackBar(
                          const SnackBar(content: Text('First name updated successful!')),
                        );
                        }catch(e){
                          print('Error updating first name: $e');
                        }
                      }
                      if (label == 'lastName') {
                        try{
                        user_service.UpdateLastName(controller.text, user);
                        ScaffoldMessenger.of(context).showSnackBar(
                          const SnackBar(content: Text('Last name updated successful!')),
                        );
                        }catch(e){
                          print('Error updating last name: $e');
                        }

                      }
                      if (label == 'mobile') {
                        try{
                          user_service.UpdateMobile(controller.text, user);
                          ScaffoldMessenger.of(context).showSnackBar(
                            const SnackBar(content: Text('Mobile updated successful!')),
                          );
                        }catch(e){
                          print('Error updating mobile: $e');
                        }
                      }
                      if (label == 'address') {
                        try{
                          user_service.UpdateAdress(controller.text, user);
                          ScaffoldMessenger.of(context).showSnackBar(
                            const SnackBar(content: Text('Address updated successful!')),
                          );
                        }catch(e){
                          print('Error updating address: $e');
                        }
                      }
                    });
                    // Close the expansion tile
                    // You might need to use a GlobalKey to access the ExpansionTile state
                  },
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }

  Widget _buildPasswordChangeField() {
    return Container(
      margin: const EdgeInsets.only(bottom: 12),
      decoration: BoxDecoration(
        color: Colors.white,
        borderRadius: BorderRadius.circular(8),
      ),
      child: ExpansionTile(
        title: const Text(
          'change password',
          style: TextStyle(
            fontSize: 16,
            color: Colors.black87,
          ),
        ),
        children: [
          Padding(
            padding: const EdgeInsets.all(16.0),
            child: Column(
              children: [
                TextField(
                  controller: newPasswordController,
                  obscureText: true,
                  decoration: InputDecoration(
                    hintText: 'New password',
                    border: OutlineInputBorder(),
                  ),
                ),
                SizedBox(height: 8),
                TextField(
                  controller: confirmPasswordController,
                  obscureText: true,
                  decoration: InputDecoration(
                    hintText: 'Confirm new password',
                    border: OutlineInputBorder(),
                  ),
                ),
                SizedBox(height: 8),
                ElevatedButton.icon(
                  icon: Icon(Icons.check),
                  label: Text('Change Password'),
                  onPressed: () {
                    // Validate and change password
                    if (newPasswordController.text == confirmPasswordController.text) {
                      // Change password logic here
                      try {
                        user_service.UpdatePassword(newPasswordController.text.toString(), user);
                        ScaffoldMessenger.of(context).showSnackBar(
                          SnackBar(content: Text('Password changed successfully! Please log in.')),
                        );
                        user_service.Logout();
                        Navigator.of(context).pushReplacementNamed('/login');
                      } catch (e) {
                        print('Error updating password: $e');
                      }
                      
                    } else {
                      ScaffoldMessenger.of(context).showSnackBar(
                        SnackBar(content: Text('Passwords do not match')),
                      );
                    }
                  },
                  style: ElevatedButton.styleFrom(
                    backgroundColor: Color(0xFF90A955),
                  ),
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }

  Widget _buildMultiSelect(String label, List<dynamic> items, List<dynamic> selectedItems) {
    return Container(
      margin: const EdgeInsets.only(bottom: 12),
      decoration: BoxDecoration(
        color: Colors.white,
        borderRadius: BorderRadius.circular(8),
      ),
      child: ExpansionTile(
        title: Text(
          label,
          style: const TextStyle(
            fontSize: 16,
            color: Colors.black87,
          ),
        ),
        children: [
          Container(
            padding: const EdgeInsets.all(16),
            child: Wrap(
              spacing: 8,
              runSpacing: 8,
              children: items.map((item) {
                var isSelected = selectedItems.contains(item);
                return FilterChip(
                  label: Text(item),
                  selected: isSelected,
                  onSelected: (bool selected) {
                    setState(() {
                      if (selected) {
                        selectedItems.add(item);
                        selected = true;
                      } else {
                        selectedItems.remove(item);
                        selected = false;
                      }
                    });
                    try {
                      _updateUserHealth(label, item, selected);
                    } catch (e) {
                      print('Error updating user health: $e');
                    }
                  },
                  selectedColor: const Color(0xFF90A955).withOpacity(0.2),
                  checkmarkColor: const Color(0xFF90A955),
                );
              }).toList(),
            ),
          ),
        ],
      ),
    );
  }
}


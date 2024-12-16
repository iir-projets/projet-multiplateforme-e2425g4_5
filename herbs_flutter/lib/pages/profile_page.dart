import 'package:flutter/material.dart';
import 'package:herbs_flutter/pages/base_layout.dart';
import 'package:image_picker/image_picker.dart';
import 'dart:io';
import 'package:flutter/foundation.dart' show kIsWeb;

class ProfilePage extends StatefulWidget {
  const ProfilePage({Key? key}) : super(key: key);

  @override
  _ProfilePageState createState() => _ProfilePageState();
}

class _ProfilePageState extends State<ProfilePage> {
  // Sample data for dropdowns
  final List<String> allergies = ['Pollen', 'Dust', 'Peanuts', 'Lactose', 'Gluten'];
  final List<String> medicines = ['Aspirin', 'Ibuprofen', 'Paracetamol', 'Antibiotics'];
  final List<String> diseases = ['Asthma', 'Diabetes', 'Hypertension', 'Heart Disease'];

  // Selected items
  List<String> selectedAllergies = [];
  List<String> selectedMedicines = [];
  List<String> selectedDiseases = [];

  // User information
  String email = 'user@example.com';
  String mobile = '+1234567890';
  String address = '123 Main St, City, Country';

  // Controllers for editing fields
  late TextEditingController emailController;
  late TextEditingController mobileController;
  late TextEditingController addressController;
  late TextEditingController newPasswordController;
  late TextEditingController confirmPasswordController;

  // Profile image
  dynamic _image;
  final ImagePicker _picker = ImagePicker();

  @override
  void initState() {
    super.initState();
    emailController = TextEditingController(text: email);
    mobileController = TextEditingController(text: mobile);
    addressController = TextEditingController(text: address);
    newPasswordController = TextEditingController();
    confirmPasswordController = TextEditingController();
  }

  @override
  void dispose() {
    emailController.dispose();
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
                        _buildEditableField('email', emailController),
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
                      if (label == 'email') email = controller.text;
                      if (label == 'mobile') mobile = controller.text;
                      if (label == 'address') address = controller.text;
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
                      ScaffoldMessenger.of(context).showSnackBar(
                        SnackBar(content: Text('Password changed successfully')),
                      );
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

  Widget _buildMultiSelect(String label, List<String> items, List<String> selectedItems) {
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
                final isSelected = selectedItems.contains(item);
                return FilterChip(
                  label: Text(item),
                  selected: isSelected,
                  onSelected: (bool selected) {
                    setState(() {
                      if (selected) {
                        selectedItems.add(item);
                      } else {
                        selectedItems.remove(item);
                      }
                    });
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


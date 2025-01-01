class Users {
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
  
  
  Users({
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
  });
}
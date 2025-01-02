class Herbs {
  final String id;
  final String name;
  final String description;
  final String imageUrl;
  final List<dynamic> properties;
  final List<dynamic> healthBenefits;
  final String origins;
  final List<dynamic> usage;
  final List<dynamic> precautions;

  Herbs({
    required this.id,
    required this.name,
    required this.description,
    required this.healthBenefits,
    required this.imageUrl,
    required this.origins,
    required this.properties,
    required this.usage,
    required this.precautions,

  });
}
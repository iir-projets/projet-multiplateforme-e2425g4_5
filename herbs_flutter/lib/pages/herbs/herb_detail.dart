import 'package:flutter/material.dart';

class HerbDetailPage extends StatefulWidget {
  final String name;
  final String imageUrl;
  final String description;
  final List<dynamic> precautions;
  final bool saved;
  final List<dynamic> properties;
  final List<dynamic> usage;
  final List<dynamic> healthBenefits;
  final String origins;
  final VoidCallback onSaveToggle;

  const HerbDetailPage({
    super.key,
    required this.name,
    required this.description,
    required this.imageUrl,
    required this.precautions,
    required this.saved,
    required this.properties,
    required this.usage,
    required this.healthBenefits,
    required this.origins,
    required this.onSaveToggle,
  });

  @override
  _HerbDetailPageState createState() => _HerbDetailPageState();
}

class _HerbDetailPageState extends State<HerbDetailPage>{

  late bool isSaved;
  @override
  void initState() {
    super.initState();
    isSaved = widget.saved;
  }
  

  

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: CustomScrollView(
        slivers: [
          // App Bar
          SliverAppBar(
            expandedHeight: 200,
            backgroundColor: const Color(0xFF90A955),
            pinned: true,
            flexibleSpace: FlexibleSpaceBar(
              title: Text(
                widget.name,
                style: TextStyle(
                  color: Colors.white,
                  fontSize: 28,
                  //background: Paint()..color = Color(0xFF90A955).withOpacity(0.7),
                ),
              ),
              background: Image.network(
                widget.imageUrl,
                fit: BoxFit.cover,
              ),
            ),
            leading: IconButton(
              icon: const Icon(Icons.arrow_back),
              onPressed: () => Navigator.pop(context),
            ),
            actions: [
              IconButton(
              icon: Icon(
                isSaved ? Icons.favorite_rounded : Icons.favorite_border_rounded, // Dynamic icon
                color: isSaved ? Color.fromARGB(255, 220, 53, 70) : Color.fromARGB(255, 220, 53, 70), // Change color as needed
              ),
              iconSize: 35,
              onPressed: (){
                
                setState(() {
                  widget.onSaveToggle();
                  isSaved = !isSaved; // Toggle saved state
                });
                
                }, // Toggle callback
            ),
            ],
          ),
          // Herb Content
          SliverToBoxAdapter(
            child: Padding(
              padding: const EdgeInsets.all(16.0),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  const SizedBox(height: 16),
                  Text(
                    widget.description,
                    style: const TextStyle(
                      fontSize: 18,
                      color: Colors.black87,
                      fontStyle: FontStyle.italic,
                      ),
                    ),
                  const SizedBox(height: 16),
                  Text(
                    'Origins',
                    style: const TextStyle(
                      fontSize: 20,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                  Text(
                    widget.origins,
                    style: const TextStyle(fontSize: 16),
                    ),
                  const SizedBox(height: 16),
                  Text(
                    'Properties',
                    style: const TextStyle(
                      fontSize: 20,
                      fontWeight: FontWeight.bold,
                    ),
                  ),

                  for (var section in widget.properties)
                    Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        
                        Text(
                          '- ${section}',
                          style: const TextStyle(fontSize: 16),
                        ),
                        
                      ],
                    ),
                  const SizedBox(height: 16),
                  Text(
                    'Usages',
                    style: const TextStyle(
                      fontSize: 20,
                      fontWeight: FontWeight.bold,
                    ),
                  ),

                  for (var section in widget.usage)
                    Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        
                        Text(
                          '- ${section}',
                          style: const TextStyle(fontSize: 16),
                        ),
                        
                      ],
                    ),
                  const SizedBox(height: 16),
                  Text(
                    'Health Benefits',
                    style: const TextStyle(
                      fontSize: 20,
                      fontWeight: FontWeight.bold,
                    ),
                  ),

                  for (var section in widget.healthBenefits)
                    Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        
                        Text(
                          '- ${section}',
                          style: const TextStyle(fontSize: 16),
                        ),
                        
                      ],
                    ),

                  
                ],
              ),
            ),
          ),
          // Comments Section
          SliverToBoxAdapter(
            child: Padding(
              padding: const EdgeInsets.all(16.0),
              child: Container(
                decoration: BoxDecoration(
                  color: const Color.fromARGB(255, 254, 238, 243),
                  borderRadius: BorderRadius.circular(12),
                  boxShadow: [
                    BoxShadow(
                      color: const Color(0xFFE8F5E9),
                      spreadRadius: 2,
                      blurRadius: 5,
                      offset: const Offset(0, 3),
                    ),
                  ],
                ),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    const Padding(
                      padding: EdgeInsets.all(10.0),
                      child: Text(
                        'Precautions',
                        style: TextStyle(
                          fontSize: 20,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                    ),
                    SizedBox(
                      height: 200, // Fixed height for the comments box
                      child:
                       
                       Container(
                            margin: const EdgeInsets.only(bottom: 16,left: 20),
                            width: 380,
                            padding: const EdgeInsets.all(12),
                            decoration: BoxDecoration(
                              color: const Color(0xFFF5F5F5),
                              borderRadius: BorderRadius.circular(8),
                              boxShadow: [
                                BoxShadow(
                                  color: Colors.grey.withOpacity(0.3),
                                  spreadRadius: 1,
                                  blurRadius: 3,
                                  offset: const Offset(0, 1),
                                ),
                                
                              ],

                            ),
                            child: Column(
                              children: [
                                for (var section in widget.precautions)
                                Column(
                                  crossAxisAlignment: CrossAxisAlignment.start,
                                  children: [
                                    
                                    Text(
                                      '-  ${section}',
                                      style: const TextStyle(fontSize: 16),
                                    ),
                                    const SizedBox(height: 12),
                                  ],
                                ),
                              ]
                            ),
                          ),
                        
                    ),
                    
                  ],
                ),
              ),
            ),
          ),
        ],
      ),
    );
  }
  

}
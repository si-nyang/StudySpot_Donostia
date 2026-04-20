const infos = [
  {
    title: "1. Home Page",
    author: "Kristyna Hrncirova",
    description: "This function displays the main page of the Study Spots website and gives access to the main features.",
    explanation: "The page is handled by the index servlet. When the user opens the website, the servlet generates the home page and shows navigation cards that link to other pages such as the list page, map page, and add location page.",
    testData: "Open the page and check whether the navigation cards are displayed correctly and link to the expected pages.",
    link: "menu"
  },
  {
    title: "2. List Page",
    author: "Max Hilding",
    description: "This function displays all study spots stored in the database and allows the user to filter them by category.",
    explanation: "The page is handled by the LocationList servlet. When the page loads, the servlet connects to the database and retrieves location data from the Locations table. If no category is selected, it shows all locations. If a category is selected, it only shows locations from that category. For each study spot, the page displays name, average rating, category, address, image, and a link to the detail page.",
    testData: "Uses examples with different categories such as Cafe, Library, and University.",
    link: "LocationList"
  },
  {
    title: "3. Detail Page",
    author: "Shinyang Park",
    description: "This function displays detailed information about one selected study spot.",
    explanation: "The LocationDetail servlet reads the location id from the URL and uses it to load one record from the database through the LocationData class. If the location exists, the servlet generates a detail page showing the category, name, rating, description, address, hours, tags, and image of the selected study spot. If no matching record is found, it displays a 'Location not found' message.",
    testData: "Use an existing location id that has complete data such as category, name, description, address, hours, tags, rating, and an image file.",
    link: "LocationDetail?id=1"
  },
  {
    title: "4. Map Page",
    author: "Shinyang Park",
    description: "This function displays study spots on an interactive map so users can view their locations visually.",
    explanation: "The map page loads a Google Map and requests location data from the mapJson servlet using fetch. The servlet reads the study spot name, description, longitude, and latitude from the Locations table through the LocationData class and returns them as JSON. Then JavaScript creates a marker for each location and shows an information window when the user clicks a marker.",
    testData: "Use records with valid location names, descriptions, longitude values, and latitude values so that markers appear correctly on the map.",
    link: "Map.html"
  },
  {
    title: "5. Add Location",
    author: "Chinatsu Oki",
    description: "This function allows the user to add a new study spot by filling in a form.",
    explanation: "The Add Location page contains a form where the user enters information such as category, name, description, address, hours, tags, and rating. When the form is submitted, the addLocation servlet reads the input values, combines the selected tags into one string, creates a LocationData object, inserts it into the Locations table, and then redirects the user to the home page.",
    testData: "Enter a category, location name, description, address, opening hours, one or more tags, and a numeric rating value.",
    link: "AddLocation.html"
  },
  {
    title: "6. Edit Location",
    author: "Your name",
    description: "This function allows the user to edit the information of an existing study spot.",
    explanation: "The Edit Location page loads the current data of a selected study spot into a form. After the user updates the fields and submits the form, the servlet reads the modified values, creates or updates a LocationData object, updates the corresponding record in the database, and then redirects the user to another page such as the detail page or list page.",
    testData: "Use an existing location record and change fields such as category, name, description, address, hours, tags, or rating.",
    link: "UpdateLocation?id=1"
  }
];

const container = document.getElementById("cardContainer");
for (var i = 0; i < infos.length; i++) {

  var info = infos[i];

  var card = document.createElement("a");
  card.href = info.link;
  card.className = "preview-card";

  var div = document.createElement("div");
  div.className = "preview-body";

  // title
  var h3 = document.createElement("h3");
  h3.appendChild(document.createTextNode(info.title));

  // author
  var h4 = document.createElement("h4");
  h4.appendChild(document.createTextNode("Developed by " + info.author));

  // description
  var b1 = document.createElement("b");
  b1.appendChild(document.createTextNode("Description"));
  var p1 = document.createElement("p");
  p1.appendChild(document.createTextNode(info.description));

  // explanation
  var b2 = document.createElement("b");
  b2.appendChild(document.createTextNode("How it works"));
  var p2 = document.createElement("p");
  p2.appendChild(document.createTextNode(info.explanation));

  // test data
  var b3 = document.createElement("b");
  b3.appendChild(document.createTextNode("Recommended test data"));
  var p3 = document.createElement("p");
  p3.appendChild(document.createTextNode(info.testData));

  // button text
  var span = document.createElement("span");
  span.className = "cta-btn";
  span.appendChild(document.createTextNode("Open page"));

  // append all
  div.appendChild(h3);
  div.appendChild(h4);
  div.appendChild(b1);
  div.appendChild(p1);
  div.appendChild(b2);
  div.appendChild(p2);
  div.appendChild(b3);
  div.appendChild(p3);
  div.appendChild(span);

  card.appendChild(div);
  container.appendChild(card);
}

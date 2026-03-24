// index.js
const script = document.createElement("script");
script.src = `https://maps.googleapis.com/maps/api/js?key=${API_KEY}&libraries=maps,marker&v=weekly`;
script.defer = true;

document.head.appendChild(script);
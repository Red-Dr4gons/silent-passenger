import {Component, Input, OnInit} from '@angular/core';
import {icon, latLng, Map, marker, point, polyline, tileLayer} from "leaflet";

@Component({
  selector: 'app-ride-card',
  templateUrl: './ride-card.component.html',
  styleUrls: ['./ride-card.component.css']
})
export class RideCardComponent implements OnInit {
  @Input() transfer: any | undefined;

  route: any;
  layersControl: any;
  options: any;

  // Define our base layers so we can reference them multiple times
  streetMaps = tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    detectRetina: true,
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
  });
  wMaps = tileLayer('http://maps.wikimedia.org/osm-intl/{z}/{x}/{y}.png', {
    detectRetina: true,
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
  });

  constructor() {
  }

  ngOnInit(): void {
    const path = this.transfer.points.split(";")
    path.pop();
    // Marker for the top of Mt. Ranier
    const summit = marker([+path[0].split(',')[0], +path[0].split(',')[1]], {
      icon: icon({
        iconSize: [25, 41],
        iconAnchor: [13, 41],
        iconUrl: 'leaflet/marker-icon.png',
        shadowUrl: 'leaflet/marker-shadow.png'
      })
    });

    // Marker for the parking lot at the base of Mt. Ranier trails
    const paradise = marker([+path[path.length - 1].split(',')[0], +path[path.length - 1].split(',')[1]], {
      icon: icon({
        iconSize: [25, 41],
        iconAnchor: [13, 41],
        iconUrl: 'leaflet/marker-icon.png',
        iconRetinaUrl: 'leaflet/marker-icon-2x.png',
        shadowUrl: 'leaflet/marker-shadow.png'
      })
    });
    let wholeRoute = []

    for (let i = 0; i < path.length; i++) {
      const split = path[i].split(',')
      wholeRoute.push([+split[0], +split[1]])
    }
    // @ts-ignore
    this.route = polyline(wholeRoute);

    this.layersControl = {
      baseLayers: {
        'Street Maps': this.streetMaps,
        'Wikimedia Maps': this.wMaps
      },
      overlays: {
        'Mt. Rainier Summit': summit,
        'Mt. Rainier Paradise Start': paradise,
        'Mt. Rainier Climb Route': this.route
      }
    };

    this.options = {
      layers: [this.streetMaps, this.route, summit, paradise],
      zoom: 7,
      center: latLng([46.879966, -121.726909])
    };
  }


  onMapReady(map: Map) {
    map.fitBounds(this.route.getBounds(), {
      padding: point(24, 24),
      maxZoom: 12,
      animate: true
    });
  }
}

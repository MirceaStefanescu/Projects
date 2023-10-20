import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  title = 'dashboardFrontEnd';
  isButtonClicked = true;

  constructor(private router: Router, private cdr: ChangeDetectorRef) {}

  ngOnInit(): void {}

  handleButtonClick(route: string): void {
    this.isButtonClicked = true;

    this.router
      .navigate([`/${route}`])
      .then(() => {
        this.isButtonClicked = false;
        this.cdr.detectChanges(); // Detect changes after updating the value
      })
      .catch((error) => {
        console.error(`Navigation to ${route} failed:`, error);
      });
  }
}

import { Component, AfterViewInit } from '@angular/core';
import { Chart } from 'chart.js/auto';
import { ArticleService } from '../../../services/article/article.service';
import { PlantesService } from '../../../services/plantes/plantes.service';
import { JoinstatsService } from '../../../services/joinstats/joinstats.service';
import { FavorisService } from '../../../services/favoris/favoris.service';


@Component({
  selector: 'app-charts',
  templateUrl: './charts.component.html',
  styleUrls: ['./charts.component.css']
})
export class ChartsComponent implements AfterViewInit {
  public top5Herbs: any;
  public top5Articles: any;
  public plantRegionChart: any;
  public totalCountsChart: any;

  constructor(
    private articleService: ArticleService,
    private favorisService: FavorisService,
    private plantesService: PlantesService,
    private joinstatsService: JoinstatsService
  ) {}

  ngAfterViewInit(): void {
    this.fetchTop5Articles();
    this.fetchTop5Herbs();
    this.fetchPlantCountsByRegion();
    this.fetchTotalCounts();
  }

  private fetchTop5Articles(): void {
    this.articleService.getTop5Articles().subscribe((data) => {
      const labels = Object.keys(data).map((key) => `${key}`);
      const values = Object.values(data);

      this.top5Articles = new Chart('topArticlesChart', {
        type: 'bar',
        data: {
          labels,
          datasets: [
            {
              label: 'Views',
              data: values,
              backgroundColor: ['#e7cccc', '#c1cfa1', '#eaaaaa', '#515c37', '#ede8dc'],
            },
          ],
        },
        options: { responsive: true, plugins: { legend: { position: 'top' } } },
      });
    });
  }

  private fetchTop5Herbs(): void {
    this.favorisService.getTop5Plantes().subscribe((data) => {
      const labels = Object.keys(data).map((key) => `${key}`);
      const values = Object.values(data);

      this.top5Herbs = new Chart('topHerbsChart', {
        type: 'bar',
        data: {
          labels,
          datasets: [
            {
              label: 'Views',
              data: values,
              backgroundColor: ['#e7cccc', '#c1cfa1', '#eaaaaa', '#515c37', '#ede8dc'],
            },
          ],
        },
        options: { responsive: true, plugins: { legend: { position: 'top' } } },
      });
    });
  }

  private fetchPlantCountsByRegion(): void {
    this.plantesService.getPlantesCountByRegion().subscribe((data) => {
      const labels = Object.keys(data);
      const values = Object.values(data);

      this.plantRegionChart = new Chart('plantRegionChart', {
        type: 'pie',
        data: {
          labels,
          datasets: [
            {
              label: 'Region Count',
              data: values,
              backgroundColor: ['#e7cccc', '#c1cfa1', '#eaaaaa', '#515c37', '#ede8dc'],
            },
          ],
        },
        options: { responsive: true, plugins: { legend: { position: 'top' } } },
      });
    });
  }

  private fetchTotalCounts(): void {
    this.joinstatsService.getCounts().subscribe((data) => {
      const labels = ['Maladies', 'Medicaments', 'Allergies'];
      const values = [data.maladies, data.medicaments, data.allergies];

      this.totalCountsChart = new Chart('totalCountsChart', {
        type: 'bar',
        data: {
          labels,
          datasets: [
            {
              label: 'Counts',
              data: values,
              backgroundColor: ['#e7cccc', '#c1cfa1', '#eaaaaa'],
            },
          ],
        },
        options: { responsive: true, plugins: { legend: { position: 'top' } } },
      });
    });
  }
}

import { Component, Input, OnChanges } from '@angular/core';
import { DomSanitizer, SafeHtml } from '@angular/platform-browser';

@Component({
  selector: 'app-vital-card',
  imports: [],
  templateUrl: './vital-card.html',
  styleUrl: './vital-card.css',
})
export class VitalCard implements OnChanges {
  @Input() icon = '';
  @Input() label = '';
  @Input() value = '';
  @Input() unit = '';
  @Input() trend: 'up' | 'down' | 'stable' = 'stable';
  @Input() data: number[] = [];
  @Input() color = '#0d9488';

  sparklinePath: SafeHtml = '';

  constructor(private sanitizer: DomSanitizer) {}

  ngOnChanges(): void {
    this.sparklinePath = this.sanitizer.bypassSecurityTrustHtml(this.generateSparklineSvg());
  }

  get trendIcon(): string {
    if (this.trend === 'up') return 'ri-arrow-up-line';
    if (this.trend === 'down') return 'ri-arrow-down-line';
    return 'ri-subtract-line';
  }

  get trendColor(): string {
    if (this.trend === 'up') return '#f59e0b';
    if (this.trend === 'down') return '#3b82f6';
    return '#10b981';
  }

  get iconBgColor(): string {
    return this.color + '18';
  }

  private generateSparklineSvg(): string {
    if (!this.data || this.data.length < 2) return '';

    const width = 200;
    const height = 40;
    const padding = 2;

    const min = Math.min(...this.data);
    const max = Math.max(...this.data);
    const range = max - min || 1;

    const points = this.data.map((v, i) => {
      const x = padding + (i / (this.data.length - 1)) * (width - padding * 2);
      const y = padding + (1 - (v - min) / range) * (height - padding * 2);
      return `${x},${y}`;
    });

    return `<svg viewBox="0 0 ${width} ${height}" preserveAspectRatio="none" style="width:100%;height:40px;">
      <polyline
        points="${points.join(' ')}"
        fill="none"
        stroke="${this.color}"
        stroke-width="1.5"
        stroke-linecap="round"
        stroke-linejoin="round"
      />
    </svg>`;
  }
}

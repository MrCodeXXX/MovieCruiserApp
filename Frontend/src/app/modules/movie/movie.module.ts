import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ThumbnailComponent } from '../movie/components/thumbnail/thumbnail.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { MovieService } from  './movie.service';
import { MatCardModule } from '@angular/material/card';

import { ContainerComponent } from 'src/app/modules/movie/components/container/container.component';
import { WatchlistComponent } from './components/watchlist/watchlist.component';
import { TmdbContainerComponent } from './components/tmdb-container/tmdb-container.component';
import { MatButton, MatButtonModule } from '@angular/material/button';
import {MatSnackBarModule} from '@angular/material/snack-bar';

import {MatInputModule} from '@angular/material/input';
import { FormsModule } from '@angular/forms';

import { MoviedialogComponent } from './components/moviedialog/moviedialog.component';
import { MovieRouterModule } from './movie.route';
import { SearchComponent } from './components/search/search.component';
import { TokenInterceptor } from './interceptor.service';
@NgModule({
  declarations: [ThumbnailComponent,MoviedialogComponent, ContainerComponent, WatchlistComponent, TmdbContainerComponent, SearchComponent],
  imports: [
    CommonModule,
    HttpClientModule,
    MovieRouterModule,
    MatCardModule,
    FormsModule,
   MatButtonModule,
   MatSnackBarModule,MatInputModule
  ],
  entryComponents:[MoviedialogComponent],
  providers:[MovieService, {
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptor,
    multi:true
  }],
  exports: [ThumbnailComponent,
  MovieRouterModule,MoviedialogComponent]
})
export class MovieModule { }

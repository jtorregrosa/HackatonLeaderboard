import { Route } from '@angular/router';

import { UserRouteAccessService } from 'app/core';
import { LeaderboardComponent } from './leaderboard.component';

export const LEADERBOARD_ROUTE: Route = {
    path: 'leaderboard',
    component: LeaderboardComponent,
    data: {
        authorities: [],
        pageTitle: 'leaderboard.title'
    },
    canActivate: [UserRouteAccessService]
};

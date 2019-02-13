import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HackatonLeaderboardSharedModule } from '../shared';

import { LEADERBOARD_ROUTE, LeaderboardComponent } from './';

@NgModule({
    imports: [HackatonLeaderboardSharedModule, RouterModule.forRoot([LEADERBOARD_ROUTE], { useHash: true })],
    declarations: [LeaderboardComponent],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class HackatonLeaderboardAppLeaderboardModule {}

import { Component, OnInit } from '@angular/core';
import { LeaderboardService } from './leaderboard.service';
import { IScore } from 'app/shared/model/score.model';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { ILeaderboard } from 'app/shared/model/leaderboard.model';
import { ifError } from 'assert';

@Component({
    selector: 'jhi-leaderboard',
    templateUrl: './leaderboard.component.html',
    styleUrls: ['leaderboard.component.scss']
})
export class LeaderboardComponent implements OnInit {
    leaderboard: ILeaderboard;
    message: string;
    totalItems: number;
    links: any;
    private timer;

    constructor(
        protected leaderboardService: LeaderboardService,
        protected jhiAlertService: JhiAlertService,
        protected parseLinks: JhiParseLinks
    ) {
        this.leaderboard = {
            entries: []
        };
        this.message = 'LeaderboardComponent message';
    }

    loadAll() {
        this.leaderboardService
            .query()
            .subscribe(
                (res: HttpResponse<ILeaderboard>) => this.paginateScores(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    protected paginateScores(data: ILeaderboard, headers: HttpHeaders) {
        this.leaderboard = data;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    fullscreen() {
        const element = document.getElementsByClassName('leaderboard-container')[0];

        if (element['requestFullScreen']) {
            element['requestFullScreen']();
        } else if (element['mozRequestFullScreen']) {
            /* Firefox */
            element['mozRequestFullScreen']();
        } else if (element['webkitRequestFullScreen']) {
            /* Chrome, Safari and Opera */
            element['webkitRequestFullScreen']();
        } else if (element['msRequestFullScreen']) {
            /* IE/Edge */
            element['msRequestFullScreen']();
        }
    }

    ngOnInit() {
        this.loadAll();

        this.timer = setInterval(() => this.loadAll(), 10000);
    }
}

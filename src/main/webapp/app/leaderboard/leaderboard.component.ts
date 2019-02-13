import { Component, OnInit } from '@angular/core';
import * as $ from 'jquery';
import { LeaderboardService } from './leaderboard.service';
import { IScore } from 'app/shared/model/score.model';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { ILeaderboard } from 'app/shared/model/leaderboard.model';

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

    ngOnInit() {
        this.loadAll();

        // global vars
        const winWidth = $(window).width();
        const winHeight = $(window).height();

        // set initial div height / width
        $('.leaderboard-container').css({
            position: 'absolute',
            top: 0,
            bottom: 0,
            left: 0,
            right: 0
        });

        const element = $('.leaderboard-container').get(0);
        if (element.requestFullscreen) {
            element.requestFullscreen();
        }
    }
}

import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ILeaderboard } from 'app/shared/model/leaderboard.model';

type EntityResponseType = HttpResponse<ILeaderboard>;

@Injectable({ providedIn: 'root' })
export class LeaderboardService {
    public resourceUrl = SERVER_API_URL + 'api/leaderboard';

    constructor(protected http: HttpClient) {}

    query(req?: any): Observable<EntityResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ILeaderboard>(this.resourceUrl, { params: options, observe: 'response' });
    }
}

import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IStage } from 'app/shared/model/stage.model';

type EntityResponseType = HttpResponse<IStage>;
type EntityArrayResponseType = HttpResponse<IStage[]>;

@Injectable({ providedIn: 'root' })
export class StageService {
    public resourceUrl = SERVER_API_URL + 'api/stages';

    constructor(protected http: HttpClient) {}

    create(stage: IStage): Observable<EntityResponseType> {
        return this.http.post<IStage>(this.resourceUrl, stage, { observe: 'response' });
    }

    update(stage: IStage): Observable<EntityResponseType> {
        return this.http.put<IStage>(this.resourceUrl, stage, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IStage>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IStage[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}

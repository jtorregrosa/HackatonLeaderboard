import { IScore } from 'app/shared/model//score.model';

export interface IStage {
    id?: number;
    name?: string;
    description?: string;
    scores?: IScore[];
}

export class Stage implements IStage {
    constructor(public id?: number, public name?: string, public description?: string, public scores?: IScore[]) {}
}

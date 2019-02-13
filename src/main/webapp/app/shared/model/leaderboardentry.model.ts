export interface ILeaderboardEntry {
    name?: string;
    value?: number;
}

export class LeaderboardEntry implements ILeaderboardEntry {
    constructor(public name?: string, public value?: number) {}
}

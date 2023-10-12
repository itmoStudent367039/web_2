export class CacheWorker {
  private cache: Promise<Cache> = caches.open("PointStorage");

  async putAllPoints(data: string, url: string) {
    const work = await this.cache;
    await work?.put(url, new Response(data));
  }

  async clearCache() {
    const openCache = await this.cache;
    const keys = await openCache.keys();
    if (keys) {
      for (const request of keys) {
        await openCache.delete(request.url);
      }
    }
  }

  async getAllCachedPoints(): Promise<string> | null {
    const openCache = await this.cache;
    const response = await openCache.match("./data.html");
    try {
      return await response.text();
    } catch (e) {
      return null;
    }
  }
}
